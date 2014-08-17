package com.jason.qing.infrastruture.persist.elasticsearch.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.jason.framework.orm.Page;
import com.jason.framework.util.html.HtmlHelper;
import com.jason.qing.domain.article.Article;
import com.jason.qing.infrastruture.persist.elasticsearch.ArticleIndexRepository;
import com.jason.qing.infrastruture.persist.elasticsearch.ElasticsearchHelper;

@Repository
public class ElasticsearchArticleIndexRepository implements
		ArticleIndexRepository,InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final static String INDICE = "articles";
	private final static String TYPE = "article";
	
	//定义字段常量
	private final static String TITLE = "title";
	private final static String SUMMARY = "summary";
	
	@Autowired
	private TransportClient transportClient;

	
	@Override
	public void afterPropertiesSet() throws Exception {
		initArticleIndexMapping();
	}

	
	private void initArticleIndexMapping() {
		try {
			boolean isExists = ElasticsearchHelper.isExistsIndex(transportClient, INDICE);
			if(!isExists){
				//create index
				transportClient.admin().indices().prepareCreate(INDICE).execute().actionGet();
			    //crate mapping
				XContentBuilder mapping = XContentFactory.jsonBuilder().startObject()
				        .startObject(TYPE)
				        	.startObject("_all")      
					        	.field("indexAnalyzer", "ik")
					            .field("searchAnalyzer", "ik")
					        .endObject()
					        .startObject("properties")      
				              	.startObject(TITLE)
				              	  .field("type", "string")      
					              .field("indexAnalyzer", "ik")
					              .field("searchAnalyzer", "ik")
					            .endObject()  
					            .startObject(SUMMARY)
				              	  .field("type", "string")      
					              .field("indexAnalyzer", "ik")
					              .field("searchAnalyzer", "ik")
					            .endObject()  
				             .endObject()
				          .endObject()
				       .endObject();
				
				PutMappingRequest mappingRequest = Requests.putMappingRequest(INDICE).type(TYPE).source(mapping);
			    transportClient.admin().indices().putMapping(mappingRequest).actionGet();
			    
			    logger.debug("create index and mapping are success!");
			}else{
				logger.debug("Index already exists!");
			}
			
		} catch (Exception e) {
			logger.error("create index and mapping are failure!", e);
		}
	}


	@Override
	public void index(Article entity) {
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
										.field(TITLE, entity.getTitle())
										.field(SUMMARY, HtmlHelper.filterHtml(entity.getSummary()))
										.endObject();
			transportClient.prepareIndex(INDICE, TYPE,String.valueOf(entity.getId()))
							.setSource(builder).execute().actionGet();
			
			logger.debug("index entity ok!");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void index(List<Article> list) {
		if(list.size()>0){
			try {
				BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
				
				for (Article article:list) {
					XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
							.field(TITLE, article.getTitle())
							.field(SUMMARY, HtmlHelper.filterHtml(article.getSummary()))
							.endObject();
					bulkRequest.add(transportClient.prepareIndex(INDICE, TYPE,String.valueOf(article.getId())).setSource(builder));
				}
				
				BulkResponse bulkResponse = bulkRequest.execute().actionGet();
				if (bulkResponse.hasFailures()) {
					logger.debug("bulkResponse.hasFailures()");
				}else{
					logger.debug("bulk index ok！");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Article get(long id) {
		GetResponse getResponse = transportClient.prepareGet(INDICE, TYPE, String.valueOf(id))
                .execute()
                .actionGet();
		if(getResponse.isExists()){
			Map<String, Object> map = getResponse.getSource();
			String title = (String) map.get(TITLE);
			String summary = (String) map.get(SUMMARY);
			String idStr = getResponse.getId();
			logger.debug("get id : {}",idStr);
			
			Article article = new Article();
			article.setId(Long.parseLong(idStr));
			article.setTitle(title);
			article.setSummary(summary);
			return article;
		}else{
			return null;
		}
	}

	@Override
	public void delete(long id) {
		DeleteResponse deleteResponse = transportClient.prepareDelete(INDICE, TYPE, String.valueOf(id))
                .execute()
                .actionGet();
		logger.debug("delete id : {}",deleteResponse.getId());
	}

	@Override
	public void deleteAll() {
		MatchAllQueryBuilder allQueryBuilder = QueryBuilders.matchAllQuery();
		transportClient.prepareDeleteByQuery(INDICE)
						.setTypes(TYPE)
						.setQuery(allQueryBuilder)
						.execute()
						.actionGet();
		logger.debug("delete All ");
	}
	
	@Override
	public List<Article> query(String queryString,int size) {
		Assert.hasText(queryString, "queryString must not be empty!");
		SearchResponse searchResponse = transportClient.prepareSearch(INDICE)
                .setTypes(TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.queryString(queryString))
                .setFrom(0).setSize(size).setExplain(true)
                .execute()
                .actionGet();
		
		SearchHits hits = searchResponse.getHits(); 
        long total = hits.getTotalHits();
        logger.debug("search articles result total:{}",total);
        List<Article> list = new ArrayList<Article>();
        Article article = null;
        for (SearchHit hit : hits) {
        	
            Long id = (Long) hit.getSource().get("id");
            String  title = (String) hit.getSource().get(TITLE);
            String summary = (String) hit.getSource().get(SUMMARY);
            logger.debug("id:{},title:{},summary:{}",id,title,summary);
            
            article = new Article();
            article.setId(id);
            article.setTitle(title);
            article.setSummary(summary);
            list.add(article);
        } 
		return list;
	}

	@Override
	public Page<Article> queryPage(Page<Article> page, String queryString) {
		Assert.notNull(page, "page must not null");
		Assert.hasText(queryString, "queryString must not be empty!");
		SearchResponse searchResponse = transportClient.prepareSearch(INDICE)
                .setTypes(TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.queryString(queryString))
                .setFrom(page.getFirst()-1).setSize(page.getPageSize()).setExplain(true)
                .execute()
                .actionGet();
		
		SearchHits hits = searchResponse.getHits(); 
        long total = hits.getTotalHits();
        logger.debug("search articles result total:{}",total);
        List<Article> list = new ArrayList<Article>();
        Article article = null;
        for (SearchHit hit : hits) {
            long id = Long.parseLong(hit.getId());
            String  title = (String) hit.getSource().get(TITLE);
            String summary = (String) hit.getSource().get(SUMMARY);
            logger.debug("id:{},title:{},summary:{}",id,title,summary);
            
            article = new Article();
            article.setId(id);
            article.setTitle(title);
            article.setSummary(summary);
            list.add(article);
        } 
        page.setTotalCount(total);
        page.setResult(list);
		return page;
	}


	

}
