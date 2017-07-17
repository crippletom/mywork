package com.xx.auth.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 
 * @author quqiang
 *
 */
public class MyAuthenticationToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3898531582979829062L;
	
	private String verifyCode;
	
	public MyAuthenticationToken(){}
	
	public MyAuthenticationToken(String username,String password,String verifyCode){
		super(username,password.toCharArray(),"");
		this.verifyCode=verifyCode;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
