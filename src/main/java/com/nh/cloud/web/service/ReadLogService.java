package com.nh.cloud.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nh.cloud.web.mapper.ReadLogMapper;

@Component
@Service
public class ReadLogService {
	
	@Autowired
	private ReadLogMapper readLogMapper;
	
	public List<Map<String, Object>> selectFilteredLog(int page, int size, Map<String, Object> params){
		
		int offset = (page - 1) * size;
		params.put("offset", offset);
		params.put("size", size);
		
		return readLogMapper.selectFilteredLog(params);
	}
	
	public int countHistories(Map<String, Object> params) {
		return readLogMapper.countHistories(params);
	}
}

