package com.spring.greencampus.ems.login.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.greencampus.ems.login.entity.User;

@Repository
public class CustomUserInterfaceImpl implements CustomUserInterface {

	@PersistenceContext
	EntityManager em;
	@Override
	@Transactional
	public User insertUser(User user) {
		// TODO Auto-generated method stub
		em.merge(user);
		return user;
	}

}
