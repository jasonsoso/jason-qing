package com.jason.qing.infrastruture.persist.elasticsearch;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jason.qing.domain.article.Article;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/META-INF/spring/application-elasticsearch.xml"})
public class ElasticsearchArticleIndexRepositoryTest extends AbstractTestBase {

	@Autowired
	private ArticleIndexRepository articleIndexRepository;
	
	@Test
	public void testQueryByName(){
		Article entity = new Article();
		entity.setId(1);
		entity.setTitle("title");
		entity.setSummary("summary");
		articleIndexRepository.index(entity);
	}
	
}
