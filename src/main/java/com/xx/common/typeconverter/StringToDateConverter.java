package com.xx.common.typeconverter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.xx.common.tools.DateTools;

public class StringToDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		if(!StringUtils.hasLength(source))
		return null;
		return DateTools.parse(source);
	}

}
