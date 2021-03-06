package com.jason.qing.application.article.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.framework.orm.hibernate.query.HQLQuery;
import com.jason.framework.util.html.SubStringHTML;
import com.jason.qing.application.article.ArticleService;
import com.jason.qing.domain.article.Article;
import com.jason.qing.infrastruture.persist.elasticsearch.ArticleIndexRepository;
import com.jason.qing.infrastruture.persist.hibernate.ArticleRepository;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleIndexRepository articleIndexRepository;

	@Override
	public void delete(Long id) {
		articleRepository.delete(id);
	}

	@Override
	public void store(Article entity) {
		entity.setSummary(SubStringHTML.subStringHTML(entity.getContent(), 300));
		articleRepository.store(entity);
		articleIndexRepository.index(entity);
	}

	@Override
	public Article get(Long id) {
		return articleRepository.get(id);
	}

	@Override
	public List<Article> query(String sql, Map<String, Object> values) {
		return articleRepository.query(sql, values);
	}

	@Override
	public Page<Article> queryPage(Page<Article> page, String hql, Map<String, Object> values) {
		return articleRepository.queryPage(page, hql, values);
	}

	@Override
	public Article getPrev(Article article) {
		String hql = "select a from Article a where id=(select max(id) from Article where id < ? and userInfo.id=?)";
		return (Article) articleRepository.queryUnique(hql, article.getId(),article.getUserInfo().getId());
	}

	@Override
	public Article getNext(Article article) {
		String hql = "select a from Article a where id=(select min(id) from Article where id > ? and userInfo.id=?)";
		return (Article) articleRepository.queryUnique(hql, article.getId(),article.getUserInfo().getId());
	}

	@Override
	public void indexAll() {
		HQLQuery query = new HQLQuery().table("Article");
		List<Article> list = query(query.hql(), query.values());
		articleIndexRepository.index(list);
	}

	@Override
	public Page<Article> search(Page<Article> page, String q) {
		return articleIndexRepository.queryPage(page, q);
	}

}
