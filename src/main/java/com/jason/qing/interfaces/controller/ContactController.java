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
import com.jason.framework.web.support.ControllerSupport;
import com.jason.qing.application.article.ArticleService;
import com.jason.qing.application.contact.ContactService;
import com.jason.qing.domain.article.Article;
import com.jason.qing.domain.contact.Contact;
import com.jason.security.util.ShiroUserUtils;


@Controller
@RequestMapping(value = "/contact")
public class ContactController extends ControllerSupport {
	
	private static final String REDIRECT_LIST = "redirect:/contact/list";
	private static final String FORM = "contact/form";
	private static final String LIST = "contact/list";
	private static final String INDEX = "contact/index";
	
	
	@Autowired
	private ContactService contactService;
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = { "/", "","index" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		Article article = articleService.get(43L);
		model.addAttribute("article", article);
		return INDEX;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Page<Contact> page, HttpServletRequest request, Model model) {
		HQLQuery query = new HQLQuery().table("Contact")
									.orderBy("updatedAt desc");
		page = contactService.queryPage(page, query.hql(), query.values());
		
		model.addAttribute(page);
		return LIST;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute(new Contact());
		return FORM;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid Contact entity, BindingResult result, HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) {
		if(ShiroUserUtils.isCurrentUser()){//判断用户是否已经登陆
			if (result.hasErrors()) {
				error(model, "创建通讯录失败，请核对数据!");
				return FORM;
			}
			Date now = new Date();
			entity.setCreatedAt(now);
			entity.setUpdatedAt(now);
			
			
			contactService.store(entity);
			
			success(redirectAttributes,"创建通讯录成功！"); 
		}else {
			error(redirectAttributes, "用户没登陆!");
		}
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {

		model.addAttribute("_method", "put")
				.addAttribute(contactService.get(id));
		return FORM;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public String edit(@PathVariable("id") Long id, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {
			Contact entity = contactService.get(id);

			bind(request, entity);
			entity.setUpdatedAt(new Date());
			contactService.store(entity);
			success(redirectAttributes,"通讯录修改成功！");
		} catch (Exception e) {
			error(redirectAttributes,"修改通讯录失败，请核实数据后重新提交！");
		}
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(HttpServletRequest request,RedirectAttributes redirectAttributes) {

		String[] items = EntityUtils.nullSafe(request.getParameterValues("items"), new String[] {});
		for (String item : items) {
			contactService.delete(Long.valueOf(item));
		}
		success(redirectAttributes,"删除通讯录成功！");
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
		contactService.delete(id);
		success(redirectAttributes,"删除通讯录成功！");
		return REDIRECT_LIST;
	}
	
}
