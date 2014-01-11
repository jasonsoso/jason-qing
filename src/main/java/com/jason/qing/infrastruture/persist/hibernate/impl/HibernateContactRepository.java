package com.jason.qing.infrastruture.persist.hibernate.impl;

import org.springframework.stereotype.Repository;

import com.jason.framework.orm.hibernate.HibernateRepositorySupport;
import com.jason.qing.domain.contact.Contact;
import com.jason.qing.infrastruture.persist.hibernate.ContactRepository;

@Repository
public class HibernateContactRepository extends HibernateRepositorySupport<Long, Contact> implements ContactRepository {


}
