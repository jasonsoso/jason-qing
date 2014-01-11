package com.jason.qing.infrastruture.persist.hibernate;


import java.util.List;
import java.util.Map;

import com.jason.framework.orm.Page;
import com.jason.qing.domain.contact.Contact;


/**
 * 通讯录  持久层
 * @author Jason
 * @data 2013-12-15 上午11:30:48
 */
public interface ContactRepository {

	Contact get(Long id);

	void store(Contact entity);

	void delete(Long id);

	List<Contact> query(String queryString, Object... values);

	Page<Contact> queryPage(Page<Contact> page, String hql, Map<String, Object> values);
	
}
