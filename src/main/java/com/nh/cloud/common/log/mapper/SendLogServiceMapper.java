package com.nh.cloud.common.log.mapper;

import  java.util.HashMap;
import java.util.List;
import java.util.Map;

import  org.apache.ibatis.annotations.Mapper;
import  org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SendLogServiceMapper {

	// 처리  이력 저장
    public void  insert(HashMap<String,String> map);

}
