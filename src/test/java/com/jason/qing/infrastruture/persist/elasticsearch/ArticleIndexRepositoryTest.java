package com.jason.qing.infrastruture.persist.elasticsearch;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jason.qing.domain.article.ArticleIndex;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/META-INF/spring/application-elasticsearch.xml"})
public class ArticleIndexRepositoryTest {
	/*
	private static Logger logger = LoggerFactory.getLogger(ArticleIndexRepositoryTest.class);

    @Autowired
    private ArticleIndexRepository articleIndexRepository;

    //@Before
    //public void emptyData(){
    	//articleIndexRepository.deleteAll();
    //}

    @Test
    public void testIndex(){

        ArticleIndex article = new ArticleIndex();
        article.setId("25");
        article.setTitle("测试elasticsearch分词器的效果");

        articleIndexRepository.save(article);

        ArticleIndex indexedArticle = articleIndexRepository.findOne(article.getId());
        assertThat(indexedArticle,is(notNullValue()));
        assertThat(indexedArticle.getId(),is(article.getId()));
    }
    @Test
    public void testSearch(){
    	Page<ArticleIndex> page = articleIndexRepository.findAll(new PageRequest(0, 10));
    	for (ArticleIndex  ai : page) {
			logger.info("--1------------id:{},title:{}",ai.getId(),ai.getTitle());
		}
    	
    	
    	
    	QueryBuilder queryBuilder = QueryBuilders.termQuery("title","中国人");
    	
    	Page<ArticleIndex> ArticleIndexs =  articleIndexRepository.search(queryBuilder,new PageRequest(0,20));
    	for (ArticleIndex  ai : ArticleIndexs) {
			logger.info("--2------------id:{},title:{}",ai.getId(),ai.getTitle());
		}
    	
    	QueryBuilder queryBuilder2 = QueryBuilders.queryString("中国人");
    	
    	Page<ArticleIndex> ArticleIndexss =  articleIndexRepository.search(queryBuilder2,new PageRequest(0,20));
    	for (ArticleIndex  ai : ArticleIndexss) {
			logger.info("--3------------id:{},title:{}",ai.getId(),ai.getTitle());
		}
    }*/
}
