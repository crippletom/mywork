package com.xx.common.tools;

/**
 * 
 * @author quqiang
 *
 */
public class PropertyTools {
	
	/**
	 * 用户
	 */
	public static final String AUTH_USER="AUTH_USER";
	/**
	 * 验证码
	 */
	public static final String VERIFY_CODE="VERIFY_CODE";
	/**
	 * 是否需要验证码
	 */
	private static boolean verifyCode=true;
	
	public static boolean isVerifyCode(){
		return verifyCode;
	}

}
