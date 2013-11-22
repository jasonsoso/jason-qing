package com.jason.qing.infrastruture.persist.hibernate;


import java.util.List;
import java.util.Map;

import com.jason.framework.orm.Page;
import com.jason.qing.domain.article.Article;

/**
 * 
 * @author Jason
 * @date 2013-1-27 上午10:47:25
 */
public interface ArticleRepository {

	Article get(Long id);

	void store(Article entity);

	void delete(Long id);

	List<Article> query(String queryString, Object... values);

	Page<Article> queryPage(Page<Article> page, String hql, Map<String, Object> values);
	
	Object queryUnique(String queryString, Object... values);
}
