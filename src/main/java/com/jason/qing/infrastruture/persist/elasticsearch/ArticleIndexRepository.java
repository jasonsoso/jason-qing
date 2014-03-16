package com.jason.qing.infrastruture.persist.elasticsearch;

import java.util.List;

import com.jason.framework.orm.Page;
import com.jason.qing.domain.article.Article;

/**
 * 文章 索引类
 * @author Jason
 * @date 2014年3月15日 下午9:53:53
 */
public interface ArticleIndexRepository {

	Article get(long id);

	void index(Article entity);
	
	void index(List<Article> list);
	
	void delete(long id);
	
	void deleteAll();
	
	/**
	 * 根据关键字进行搜索
	 * @param queryString 关键字
	 * @param size 记录条数
	 * @return List<Article>
	 */
	List<Article> query(String queryString,int size);

	/**
	 * 根据关键字分页搜索
	 * @param page 分页
	 * @param queryString 关键字
	 * @return Page<Article>
	 */
	Page<Article> queryPage(Page<Article> page, String queryString);
}
