package com.jason.qing.interfaces.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jason.framework.orm.Page;
import com.jason.framework.orm.hibernate.query.HQLQuery;
import com.jason.framework.web.exception.ResourceNotFoundException;
import com.jason.framework.web.support.ControllerSupport;
import com.jason.qing.application.article.ArticleService;
import com.jason.qing.domain.article.Article;
import com.jason.security.model.UserInfo;
import com.jason.security.repository.QueryRepository;


/**
 * 个人中心 个人首页 个人博客首页
 * @author Jason
 * @date 2013-1-27 下午08:56:37
 */
@Controller
public class UserController extends ControllerSupport {
	
	/**
	 * 公用的用戶查詢 
	 */
	@Autowired
	private QueryRepository queryRepository;
	
	@Autowired
	private ArticleService articleService;

	/*--------------------华丽的分割------------------------*/
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String index(@PathVariable("username")String username,Model model){
		return blogList(username, 1, model);
	}

	@RequestMapping(value = "/{username}/page/{pageNo}", method = RequestMethod.GET)
	public String blogList(@PathVariable("username")String username,@PathVariable("pageNo")int pageNo,Model model) {
		Page<Article> page = new Page<Article>().setPageNo(pageNo).setPageSize(5);
		
		UserInfo user = queryRepository.queryByName(username);
		
		if(null != user){
			HQLQuery query = new HQLQuery().table("select a from Article a join a.user u")
											.eq("u.username", username)
											.orderBy("a.createdAt desc");
			
			page = articleService.queryPage(page, query.hql(), query.values());
			model.addAttribute(page).addAttribute("user",user);
		}else{
			throw new ResourceNotFoundException();//404
		}
		
		return "WEB-INF/front/template/user";
	}

}
