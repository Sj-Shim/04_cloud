package com.nh.cloud.web.service;

import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.cloud.web.mapper.UserInfoMapper;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;
		
	public void addUserToManager(Map<String, String> param) {
		try {
			userInfoMapper.addUserToManager(param);
		}
		catch(PersistenceException e) {
			throw new PersistenceException(e);
		}
	}
	
	public void expireManager(Map<String, String> param) {
		try {
			
			userInfoMapper.expireManager(param);
		}
		catch(PersistenceException e) {
			throw new PersistenceException(e);
		}
	}
}
