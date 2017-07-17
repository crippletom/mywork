package com.xx.auth.web.vo;

import java.io.Serializable;

public class UserVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3020614832150047933L;
	
	private String username;
	private String password;
	private String verifyCode;
	private Boolean rememberMe;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public Boolean getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
