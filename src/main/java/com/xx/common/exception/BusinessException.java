package com.xx.common.exception;

/**
 * 业务异常
 * @author quqiang
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String msg){
		super(msg);
	}
	
}
