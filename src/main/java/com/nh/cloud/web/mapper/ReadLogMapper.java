package com.nh.cloud.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReadLogMapper {

	//처리 이력 목록 조회
    public List<Map<String, Object>> selectFilteredLog(Map<String, Object> params);
    //이력목록 개수 조회
    public int countHistories(Map<String, Object> params);
}
