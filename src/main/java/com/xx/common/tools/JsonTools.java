package com.xx.common.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xx.common.json.mapper.CustomObjectMapper;

public class JsonTools {
	
	private static ObjectMapper objectMapper;
	
	private static String errJson = "{statusCode:400,message:\"转换JSON失败\"}";
	
	public static ObjectMapper getObjectMapper(){
		if(objectMapper==null){
			synchronized (JsonTools.class) {
				if(objectMapper==null){
					objectMapper=new CustomObjectMapper();
				}
			}
		}
		return objectMapper;
	}
	
	/**
	 * 将对象转换为JSON
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		String json;
		try {
			json=getObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			json=errJson;
		}
		return json;
	}
	
	/**
	 * 将JSON转换为对象
	 * @param json
	 * @param clz
	 * @return
	 */
	public static <B> B toBean(String json,Class<B> clz){
		try {
			return getObjectMapper().readValue(json, clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param json
	 * @param clz
	 * @return
	 */
	public static <B> List<B> toList(String json,Class<B> clz){
		JavaType javaType=getObjectMapper().getTypeFactory().constructCollectionType(List.class, clz);
		try {
			List<B> list=getObjectMapper().readValue(json, javaType);
			return list;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<B>();
	}
	
}
