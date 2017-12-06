package com.spring.greencampus.ems.login.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.spring.greencampus.ems.login.entity.Access;


public interface AccessRepository extends Repository<Access, Long>{

	Access findByUserid(long userid);

}
