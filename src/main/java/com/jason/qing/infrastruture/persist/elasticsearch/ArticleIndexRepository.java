package com.jason.qing.infrastruture.persist.elasticsearch;


import com.jason.qing.domain.article.Article;


public interface ArticleIndexRepository {

	//Article get(String id);

	void index(Article entity);

	//List<Article> query(String queryString);

	//Page<Article> queryPage(Page<Article> page, String queryString);
}
