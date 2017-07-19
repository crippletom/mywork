package com.xx.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.log.entity.AccessLog;
import com.xx.log.repository.AccessLogRepository;
import com.xx.log.service.AccessLogService;

@Service("accessLogService")
public class AccessLogServiceImpl implements AccessLogService {
	
	@Autowired
	private AccessLogRepository repository;

	@Override
	public void add(AccessLog log) {
		repository.add(log);
	}

}
