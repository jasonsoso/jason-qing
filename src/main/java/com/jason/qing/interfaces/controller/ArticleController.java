package com.jason.qing.interfaces.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jason.framework.domain.EntityUtils;
import com.jason.framework.orm.Page;
import com.jason.framework.orm.hibernate.query.HQLQuery;
import com.jason.framework.web.exception.ResourceNotFoundException;
import com.jason.framework.web.support.ControllerSupport;
import com.jason.qing.application.article.ArticleService;
import com.jason.qing.domain.article.Article;
import com.jason.security.model.UserInfo;
import com.jason.security.util.ShiroUserUtils;

/**
 * 
 * 文章管理的Controller, 使用Restful风格的Urls:
 * 
 * List   page        : GET  /article/list
 * Create page        : GET  /article/create
 * Create action      : POST /article/create
 * Show   page        : GET  /article/{id}
 * Update page        : GET  /article/{id}/edit
 * Update action      : PUT  /article/{id}/edit
 * Delete action      : DELETE /article/{id}/delete
 * Delete many action : DELETE /article/delete
 * 
 * @author Jason
 *
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController extends ControllerSupport {
	
	private static final String REDIRECT_LIST = "redirect:/article/list";
	private static final String FORM = "article/form";
	private static final String LIST = "article/list";
	
	
	@Autowired
	private ArticleService articleService;

	/**
	 * article list
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Page<Article> page, HttpServletRequest request, Model model) {
		HQLQuery query = null;
		if(ShiroUserUtils.isCurrentUser()){
			query = new HQLQuery().table("Article")//userInfo.id=
				.eq("userInfo.id",ShiroUserUtils.getCurrentUserId())
				.orderBy("updatedAt desc");
		}else{
			query = new HQLQuery().table("Article")
				.orderBy("updatedAt desc");
		}
		
		page = articleService.queryPage(page, query.hql(), query.values());
		
		model.addAttribute(page);
		return LIST;
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute(new Article());
		return FORM;
	}

	/**
	 * @param entity
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid Article entity, BindingResult result, HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) {
		if(ShiroUserUtils.isCurrentUser()){//判断用户是否已经登陆
			if (result.hasErrors()) {
				error(model, "创建文章失败，请核对数据!");
				return FORM;
			}
			Date now = new Date();
			entity.setCreatedAt(now);
			entity.setUpdatedAt(now);
			
			UserInfo userInfo = new UserInfo(ShiroUserUtils.getCurrentUserId());
			entity.setUserInfo(userInfo);
			
			articleService.store(entity);
			
			success(redirectAttributes,"创建文章成功！"); 
		}else {
			error(redirectAttributes, "用户没登陆!");
		}
		return REDIRECT_LIST;
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {

		model.addAttribute("_method", "put")
				.addAttribute(articleService.get(id));
		return FORM;
	}

	/**
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public String edit(@PathVariable("id") Long id, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {
			Article entity = articleService.get(id);

			bind(request, entity);
			entity.setUpdatedAt(new Date());
			articleService.store(entity);
			success(redirectAttributes,"文章修改成功！");
		} catch (Exception e) {
			error(redirectAttributes,"修改文章失败，请核实数据后重新提交！");
		}
		return REDIRECT_LIST;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(HttpServletRequest request,RedirectAttributes redirectAttributes) {

		String[] items = EntityUtils.nullSafe(request.getParameterValues("items"), new String[] {});
		for (String item : items) {
			articleService.delete(Long.valueOf(item));
		}
		success(redirectAttributes,"删除文章成功！");
		return REDIRECT_LIST;
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
		articleService.delete(id);
		success(redirectAttributes,"删除文章成功！");
		return REDIRECT_LIST;
	}
	
	
	
	//----------------------------- 华丽的 分割符号--------------------------------
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		Article article = articleService.get(id);
		if (null != article) {
			Article prev = articleService.getPrev(article);
			Article next = articleService.getNext(article);
			model.addAttribute(article)
					.addAttribute("prev", prev)
					.addAttribute("next", next);
		} else {
			throw new ResourceNotFoundException();
		}
		return "WEB-INF/front/template/show";
	}
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index(Model model){
		return index(1, model);
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page/{pageNo}", method = RequestMethod.GET)
	public String index(@PathVariable("pageNo")int pageNo,Model model) {
		Page<Article> page = new Page<Article>().setPageNo(pageNo).setPageSize(10);
		
		HQLQuery query = new HQLQuery().table("select a from Article a")
											.orderBy("a.createdAt desc");
			
		page = articleService.queryPage(page, query.hql(), query.values());
		model.addAttribute(page);
		return "WEB-INF/front/template/index";
	}
	
	
}
