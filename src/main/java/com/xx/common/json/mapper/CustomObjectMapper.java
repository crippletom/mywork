package com.xx.common.json.mapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.xx.common.tools.DateTools;

public class CustomObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2717968251776247216L;

	public CustomObjectMapper() {
		super();
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);

		// 日期反序列化
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Date.class, new DateDeserializer());
		this.registerModule(module);
	}

	// 返序列化将字符串转化为Date
	private static class DateDeserializer extends JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			JsonNode node = p.getCodec().readTree(p);
			String s = node.asText();
			Date parse = DateTools.parse(s);
			return parse;
		}

	}

}
