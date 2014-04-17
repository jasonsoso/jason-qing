package com.jason.qing.interfaces.controller.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.jason.qing.domain.article.Article;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Item;

/**
 * 文章　ＲＳＳ　视图
 * @author Jason
 * @data 2014-4-17 下午06:04:14
 */
public class RssArticleView extends AbstractRssFeedView {

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel channel,
			HttpServletRequest request) {
		
		channel.setTitle("杰森轻博");
		channel.setDescription("杰森轻博");
		channel.setLink("http://qing.jasonsoso.com");
		channel.setEncoding("UTF-8");
		
		super.buildFeedMetadata(model, channel, request);
	}
	
	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		@SuppressWarnings("unchecked")
		List<Article> articles = (List<Article>) model.get("rssArticle");
		
		List<Item> items = new ArrayList<Item>();
		Item item = null;
		for (Article article:articles) {
			item = new Item();
			item.setTitle(article.getTitle());
			item.setAuthor(article.getUserInfo().getUsername());
			item.setLink("http://qing.jasonsoso.com/article/"+article.getId());
			item.setPubDate(article.getCreatedAt());
			
			Description description = new Description();
			description.setType(article.getSummary());
			description.setValue(article.getSummary());
			item.setDescription(description);
			
			items.add(item);
		}
		return items;
	}
}