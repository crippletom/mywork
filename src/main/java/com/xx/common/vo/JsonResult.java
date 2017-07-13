package com.xx.common.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4256108976154824949L;
	
	public static Integer OK = 200;
	public static Integer FAILURE = 300;
	public static Integer ERROR = 400;
	
	private Integer statusCode;

	private String message;

	private final Map<String, Object> result;

	public JsonResult() {
		result = new HashMap<String, Object>();
		this.statusCode = OK;
		this.message = "";
	}
	
	public void put(String key, Object value) {
		result.put(key, value);
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getResult() {
		return result;
	}
}
