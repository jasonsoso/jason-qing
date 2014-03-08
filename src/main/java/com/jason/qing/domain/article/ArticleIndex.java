package com.jason.qing.domain.article;


//@Document(indexName = "articles", type = "article", shards = 1, replicas = 0, refreshInterval = "-1", indexStoreType = "memory")
public class ArticleIndex {

    //@Id
    private String id;
    
    //@Field(type = String, index = analyzed, store = true, searchAnalyzer = "ik", indexAnalyzer = "ik")
    private String title;
    
    //@Field(type = String, index = analyzed, store = true, searchAnalyzer = "ik", indexAnalyzer = "ik")
    private String summary;
    
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
