package com.spring.greencampus.ems.login.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String secretMd5;
	private String email;
	private Date regestrationDate;
	private Date lastLogin;
	public User(){
		
	}
	
	public User(String secretMd5, String name, String email, Date regestrationDate, Date lastLogin) {
		super();
		this.secretMd5 = secretMd5;
		this.email = email;
		this.regestrationDate = regestrationDate;
		this.lastLogin = lastLogin;
	}

	public User(long id, String secretMd5, String name, String email, Date regestrationDate, Date lastLogin) {
		super();
		this.id = id;
		this.secretMd5 = secretMd5;
		this.email = email;
		this.regestrationDate = regestrationDate;
		this.lastLogin = lastLogin;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSecretMd5() {
		return secretMd5;
	}
	public void setSecretMd5(String secretMd5) {
		this.secretMd5 = secretMd5;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegestrationDate() {
		return regestrationDate;
	}
	public void setRegestrationDate(Date regestrationDate) {
		this.regestrationDate = regestrationDate;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}
