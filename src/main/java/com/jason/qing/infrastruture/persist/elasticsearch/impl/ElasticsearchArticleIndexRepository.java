package com.jason.qing.infrastruture.persist.elasticsearch.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.jason.framework.orm.Page;
import com.jason.qing.domain.article.Article;
import com.jason.qing.infrastruture.persist.elasticsearch.ArticleIndexRepository;

@Repository
public class ElasticsearchArticleIndexRepository implements
		ArticleIndexRepository {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final static String INDICE = "articles";
	private final static String TYPE = "article";
	
	@Autowired
	private TransportClient transportClient;

	@Override
	public void index(Article entity) {
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
										.field("title", entity.getTitle())
										.field("summary", entity.getSummary())
										.endObject();
			transportClient.prepareIndex(INDICE, TYPE,String.valueOf(entity.getId()))
							.setSource(builder).execute().actionGet();
			
			logger.debug("index(Article entity) ok!data is "+builder.toString());
		} catch (ElasticSearchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void index(List<Article> list) {
		logger.debug("List<Article> list");
		if(list.size()>0){
			try {
				BulkRequestBuilder bulkRequest = transportClient.prepareBulk();
				
				for (Article article:list) {
					XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
							.field("title", article.getTitle())
							.field("summary", article.getSummary())
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
			String title = (String) map.get("title");
			String summary = (String) map.get("summary");
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
                .setFrom(0).setSize(size).setExplain(true)                        //Page  
                .execute()
                .actionGet();
		
		SearchHits hits = searchResponse.getHits(); 
        long total = hits.getTotalHits();
        logger.debug("search articles result total:{}",total);
        List<Article> list = new ArrayList<Article>();
        Article article = null;
        for (SearchHit hit : hits) {
        	
            Long id = (Long) hit.getSource().get("id");
            String  title = (String) hit.getSource().get("title");
            String summary = (String) hit.getSource().get("summary");
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
            String  title = (String) hit.getSource().get("title");
            String summary = (String) hit.getSource().get("summary");
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
