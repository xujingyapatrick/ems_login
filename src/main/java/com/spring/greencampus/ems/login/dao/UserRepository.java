package com.spring.greencampus.ems.login.dao;

import org.springframework.data.repository.Repository;

import com.spring.greencampus.ems.login.entity.User;

public interface UserRepository extends Repository<User, Long>{
	User findById(long id);
	User findByEmailAndSecretMd5(String email, String md5);

}
