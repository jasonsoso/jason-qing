package com.jason.qing.infrastruture.persist.hibernate.impl;

import org.springframework.stereotype.Repository;

import com.jason.framework.orm.hibernate.HibernateRepositorySupport;
import com.jason.qing.domain.article.Article;
import com.jason.qing.infrastruture.persist.hibernate.ArticleRepository;

@Repository
public class HibernateArticleRepository extends HibernateRepositorySupport<Long, Article> implements ArticleRepository {

}
