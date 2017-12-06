package com.spring.greencampus.ems.login.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.greencampus.ems.login.dao.CompondAccessRepository;
import com.spring.greencampus.ems.login.entity.Access;

import javassist.expr.NewArray;

@RestController
public class AccessController {
	
	@Autowired
	CompondAccessRepository accessRepository;
	
	@RequestMapping(value="/access", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> isRequestAccessAllowd(@RequestHeader(value="X-ACCESS-ID") String id, @RequestHeader(value="X-ACCESS-TOKEN") String token){
		Access cur = accessRepository.findByUserid(Long.parseLong(id));
		if (cur == null) {
			return new ResponseEntity(new Error("User "+id +"not login!"), HttpStatus.NOT_FOUND);
		}
		else if (token==null || !cur.getToken().equals(token)) {
			return new ResponseEntity(new Error("Token for"+id +" not match!"), HttpStatus.FORBIDDEN);
		}
		else if (cur.getExpireAt().before(new Date())) {
			return new ResponseEntity(new Error("Token for"+id +" expired!"), HttpStatus.FORBIDDEN);
		}
		else{
			return new ResponseEntity(HttpStatus.OK);
		}
	}

}
