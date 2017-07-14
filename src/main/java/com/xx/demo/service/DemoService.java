package com.xx.demo.service;

import java.util.List;

import com.xx.common.vo.Page;
import com.xx.demo.entity.Demo;


public interface DemoService {
	
	public void add(Demo demo);
	public void delete(Long id);
	public void update(Demo demo);
	public Demo getById(Long id);
	public void paging(Page page);
	public List<Demo> list();
}
