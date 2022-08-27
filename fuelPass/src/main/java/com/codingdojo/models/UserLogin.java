package com.codingdojo.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserLogin {
	
	@NotNull
	@NotEmpty(message="you must provide your email")
	private String email;
	
	
	@NotNull
	private String password;


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public UserLogin() {};
	

}
