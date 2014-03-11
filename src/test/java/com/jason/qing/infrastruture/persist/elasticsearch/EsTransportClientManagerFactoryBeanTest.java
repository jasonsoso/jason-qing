package com.jason.qing.infrastruture.persist.elasticsearch;



import java.io.IOException;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EsTransportClientManagerFactoryBeanTest extends AbstractTestBase {

	@Autowired
	private ArticleIndexRepository articleIndexRepository;
	@Autowired
	private TransportClient transportClient;
	
	@Test
	public void testGetBean(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
					"classpath:/META-INF/spring/application-root.xml",
					"classpath:/META-INF/spring/application-elasticsearch.xml"});   
		context.getBean("esTransportClientManager"); 
		Assert.assertNotNull("transportClient is not null!", transportClient);
		
		try {
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
		}
		
	}
	
	/*@Test
	public void testQueryByName(){
		Article entity = new Article();
		entity.setId(1);
		entity.setTitle("title");
		entity.setSummary("summary");
		articleIndexRepository.index(entity);
	}*/
	
}
