package com.xx.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.common.vo.Page;
import com.xx.demo.entity.Demo;
import com.xx.demo.repository.DemoRepository;
import com.xx.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService{

	@Autowired
	private DemoRepository repository;
	
	@Transactional
	public void add(Demo demo){
		repository.add(demo);
	}
	
	@Transactional
	public void delete(Long id){
		repository.delete(Demo.class, id);
	}
	
	@Transactional
	public void update(Demo demo){
		Demo en=repository.get(Demo.class, demo.getId());
//		en.setBirthday(demo.getBirthday());
//		en.setGender(demo.getGender());
//		en.setName(demo.getName());
		BeanUtils.copyProperties(demo, en , "id","createTime");
		repository.update(en);
	}
	
	public Demo getById(Long id){
		return repository.get(Demo.class, id);
	}
	
	public void paging(Page page){
		repository.pagingFromNativeSQL(page, Demo.class, "select * from demo", null);
	}

	@Override
	public List<Demo> list() {
		
		String ql="FROM Demo WHERE name=:name and gender=:gender";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("name", "jack");
		params.put("gender", 1);
		return repository.queryFromJPA(ql, params);
		
//		return repository.getFromNativeSQL("select * from demo", Demo.class, null);
	}

}
