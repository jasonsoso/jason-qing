package com.jason.qing.domain.article;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "articles", type = "article", shards = 1, replicas = 0, refreshInterval = "-1", indexStoreType = "memory")
public class ArticleIndex {

    @Id
    private String id;
    
    @Field(type = String, index = analyzed, store = true, searchAnalyzer = "ik", indexAnalyzer = "ik")
    private String title;
    
    public ArticleIndex() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
