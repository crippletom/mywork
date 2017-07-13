package com.xx.demo.service.impl;

import javax.transaction.Transactional;

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
		repository.update(demo);
	}
	
	public Demo getById(Long id){
		return repository.get(Demo.class, id);
	}
	
	public void paging(Page page){
		repository.pagingFromNativeSQL(page, Demo.class, "select * from demo", null);
	}

}
