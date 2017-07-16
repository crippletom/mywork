package com.xx.demo.web.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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

	@RequiresPermissions(value="perm01")
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "demo/index";
	}
	
	@RequiresPermissions(value="user:add")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult add(Demo demo){
		//Demo demo=new Demo("jack",1);
		demo.setCreateTime(new Date());
		service.add(demo);
		return new JsonResult();
	}
	
	@RequiresPermissions(value="user:delete")
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
	public JsonResult paging(Page page){
		JsonResult result=new JsonResult();
		service.paging(page);
		result.put("page", page);
		return result;
	}
	
	@RequiresPermissions(value="perm02")
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public List<Demo> list(){
		return service.list();
	}
}
