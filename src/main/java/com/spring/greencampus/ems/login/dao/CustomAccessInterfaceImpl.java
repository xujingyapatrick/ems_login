package com.spring.greencampus.ems.login.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.greencampus.ems.login.entity.Access;

@Repository
public class CustomAccessInterfaceImpl implements CustomAccessInterface {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public Access insertAccess(Access access) {
		return em.merge(access);
		
	}

}
