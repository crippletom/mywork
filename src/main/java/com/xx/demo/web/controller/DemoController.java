package com.xx.demo.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xx.common.vo.JsonResult;
import com.xx.common.vo.Page;
import com.xx.demo.entity.Demo;
import com.xx.demo.service.DemoService;


@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@Autowired
	private DemoService service;

	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "demo/index";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult add(Demo demo){
		//Demo demo=new Demo("jack",1);
		demo.setCreateTime(new Date());
		service.add(demo);
		return new JsonResult();
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(Long id){
		service.delete(id);
		return new JsonResult();
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult update(Demo demo){
		service.update(demo);
		return new JsonResult();
	}
	
	@RequestMapping(value="/paging",method=RequestMethod.POST)
	@ResponseBody
	public Object paging(Page page){
		service.paging(page);
		return page.getData();
	}
}