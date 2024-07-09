package com.nh.cloud.web.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserInfoMapper {

	public void addUserToManager(Map<String, String> param);
		
	public void expireManager(Map<String, String> param);
}
