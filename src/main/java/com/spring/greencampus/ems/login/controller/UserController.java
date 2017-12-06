package com.spring.greencampus.ems.login.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.greencampus.ems.login.dao.CompondAccessRepository;
import com.spring.greencampus.ems.login.dao.CompondUserRepository;
import com.spring.greencampus.ems.login.entity.Access;
import com.spring.greencampus.ems.login.entity.User;
import com.spring.greencampus.ems.login.pojo.SignInForm;
import com.spring.greencampus.ems.login.pojo.SignUpForm;

import javassist.expr.NewArray;

@RestController
public class UserController {
	@Autowired
	CompondAccessRepository accessRepository;
	
	@Autowired
	CompondUserRepository userRepository;
	
	@RequestMapping(value="signup", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> signup(@RequestBody SignUpForm form){
		if(form==null || form.getEmail()==null || form.getSecret()==null || form.getName()==null){
			return new ResponseEntity(new Error("invalid signup form"), HttpStatus.BAD_REQUEST);
		}
		String md5 = form.getSecret();
		Date regisDate = new Date();
		Date lastLogin = new Date();
		User user = new User(md5, form.getName(), form.getEmail(), regisDate, lastLogin);
		userRepository.insertUser(user);
		user=userRepository.findByEmailAndSecretMd5(form.getEmail(), md5);
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastLogin);
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		Date expire = calendar.getTime();
		
		Access access = new Access(user.getId(), uuid, expire);
		accessRepository.insertAccess(access);
		
		return new ResponseEntity<Access>(access, HttpStatus.OK);
	}
	
	@RequestMapping(value="signin", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> signin(@RequestBody SignInForm form){
		if(form==null || form.getEmail()==null || form.getSecret()==null){
			return new ResponseEntity(new Error("invalid signin form"), HttpStatus.BAD_REQUEST);
		}
		User user = userRepository.findByEmailAndSecretMd5(form.getEmail(), form.getSecret());
		if(user==null){
			return new ResponseEntity(new Error("error signin info, please chech email and secret"), HttpStatus.FORBIDDEN);
		}
		String uuid = UUID.randomUUID().toString().replace("-", "");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		Date expire = calendar.getTime();
		Access access = new Access(user.getId(), uuid, expire);
		accessRepository.insertAccess(access);
		
		return new ResponseEntity<Access>(access, HttpStatus.OK);
	}
	
	

}
