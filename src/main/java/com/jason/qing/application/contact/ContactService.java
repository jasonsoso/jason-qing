package com.jason.qing.application.contact;

import java.util.List;
import java.util.Map;

import com.jason.framework.orm.Page;
import com.jason.qing.domain.contact.Contact;


/**
 * 通讯录 逻辑层
 * @author Jason
 * @data 2013-12-15 上午11:37:25
 */
public interface ContactService {

	void delete(Long id);

	void store(Contact entity);

	Contact get(Long id);

	List<Contact> query(String queryString, Object... values);

	Page<Contact> queryPage(Page<Contact> page, String hql, Map<String, Object> values);
	
}
