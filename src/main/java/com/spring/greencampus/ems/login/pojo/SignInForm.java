package com.spring.greencampus.ems.login.pojo;

public class SignInForm {
	private String email;
	private String secret;
	public SignInForm(){
		
	}
	public SignInForm(String email, String secret) {
		super();
		this.email = email;
		this.secret = secret;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	

}
