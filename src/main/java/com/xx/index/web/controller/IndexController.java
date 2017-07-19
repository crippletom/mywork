package com.xx.index.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xx.log.annotation.AccessLogFunc;

@Controller
public class IndexController {

	@AccessLogFunc("应用首页")
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
