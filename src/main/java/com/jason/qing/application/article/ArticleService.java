package com.jason.qing.application.article;

import java.util.List;
import java.util.Map;

import com.jason.framework.orm.Page;
import com.jason.qing.domain.article.Article;


public interface ArticleService {

	/**
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 
	 * @param entity
	 */
	void store(Article entity);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Article get(Long id);

	/**
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	List<Article> query(String hql,  Map<String, Object> values);

	/**
	 * 
	 * @param page
	 * @param hql
	 * @param values
	 * @return
	 */
	Page<Article> queryPage(Page<Article> page, String hql, Map<String, Object> values);
	/**
	 * 
	 * @date 2013-1-18  下午08:43:41
	 */
	Article getPrev(Article article);
	/**
	 * @date 2013-1-18  下午08:44:03
	 */
	Article getNext(Article article);
	
	/**
	 * 索引全部文章
	 */
	void indexAll();
	
}
