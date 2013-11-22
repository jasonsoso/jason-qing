package com.jason.qing.infrastruture.persist.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jason.qing.domain.article.ArticleIndex;

public interface ArticleIndexRepository extends ElasticsearchRepository<ArticleIndex,String> {
	
}
