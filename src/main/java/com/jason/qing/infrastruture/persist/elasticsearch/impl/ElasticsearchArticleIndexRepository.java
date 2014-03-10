package com.jason.qing.infrastruture.persist.elasticsearch.impl;

import org.springframework.stereotype.Repository;

import com.jason.qing.domain.article.Article;
import com.jason.qing.infrastruture.persist.elasticsearch.ArticleIndexRepository;

@Repository
public class ElasticsearchArticleIndexRepository implements
		ArticleIndexRepository {

	//@Resource(name = "transportClient")
	//@Autowired
	/*private TransportClient transportClient;

	public TransportClient getTransportClient() {
		return transportClient;
	}
	public void setTransportClient(TransportClient transportClient) {
		this.transportClient = transportClient;
	}*/

	@Override
	public void index(Article entity) {
		//if(transportClient == null){
		//	System.out.println("---------transportClient is null------------");
		//}else{
		//	System.out.println("---------transportClient is not null------------");
		//}
		System.out.println("---------index------------");
		/*try {
			transportClient
					.prepareIndex("articles", "article")
					.setSource(
							XContentFactory.jsonBuilder().startObject()
									.field("id", "1")
									.field("title", "title")
									.field("summary", "summary")
									.endObject()).execute().actionGet();
		} catch (ElasticSearchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}
