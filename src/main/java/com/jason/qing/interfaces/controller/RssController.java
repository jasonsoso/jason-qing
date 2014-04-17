package com.jason.qing.interfaces.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jason.framework.orm.Page;
import com.jason.framework.orm.hibernate.query.HQLQuery;
import com.jason.framework.web.support.ControllerSupport;
import com.jason.qing.application.article.ArticleService;
import com.jason.qing.domain.article.Article;

/**
 * ＲＳＳ　订阅　控制器
 * @author Jason
 * @data 2014-4-17 下午06:04:40
 */
@Controller
@RequestMapping(value = "/rss")
public class RssController extends ControllerSupport {
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * ＲＳＳ　文章查询
	 * @return　ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public ModelAndView article() throws Exception {
		Page<Article> page = new Page<Article>().setPageNo(1).setPageSize(10);
		
		HQLQuery query = new HQLQuery().table("Article")
							.orderBy("updatedAt desc");
		page = articleService.queryPage(page, query.hql(), query.values());
		List<Article> list = page.getResult();
		
		//model.addAttribute("rssArticle", list);
		//return "";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rssArticle", list);
		modelAndView.setViewName("rssArticleView");
		
		return modelAndView;
	}
	
}