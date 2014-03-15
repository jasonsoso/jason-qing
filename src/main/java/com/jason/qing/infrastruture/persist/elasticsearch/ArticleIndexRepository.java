package com.jason.qing.infrastruture.persist.elasticsearch;


import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.qing.domain.article.Article;


public interface ArticleIndexRepository {

	Article get(long id);

	void index(Article entity);
	
	void index(List<Article> list);
	
	void delete(long id);
	
	void deleteAll();
	
	List<Article> query(String queryString,int size);

	Page<Article> queryPage(Page<Article> page, String queryString);
}
