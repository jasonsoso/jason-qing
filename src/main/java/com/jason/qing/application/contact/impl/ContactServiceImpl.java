package com.jason.qing.application.contact.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.framework.orm.Page;
import com.jason.qing.application.contact.ContactService;
import com.jason.qing.domain.contact.Contact;
import com.jason.qing.infrastruture.persist.hibernate.ContactRepository;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void delete(Long id) {
		contactRepository.delete(id);
	}

	@Override
	public void store(Contact entity) {
		contactRepository.store(entity);
	}

	@Override
	public Contact get(Long id) {
		return contactRepository.get(id);
	}

	@Override
	public List<Contact> query(String queryString, Object... values) {
		return contactRepository.query(queryString, values);
	}

	@Override
	public Page<Contact> queryPage(Page<Contact> page, String hql, Map<String, Object> values) {
		return contactRepository.queryPage(page, hql, values);
	}

}
