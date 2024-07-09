package com.nh.cloud.common.fileinfo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FileInfoMapper {
   
	// 파일 정보 저장
    public void  insert(HashMap<String,String> map);
    
	// 파일 정보 변경
    public void  update(HashMap<String,String> map);

    //파일목록 리스트 조회
    public List<Map<String, Object>> selectPagedFiles(Map<String, Object> params);
    //파일목록 개수 조회
    public int countFiles(Map<String, Object> params);
    //파일 확장자 목록 조회
    public List<String> selectAllXcrnm();
    //선택 파일정보 조회
    public Map<String, Object> getSelectedFileInfo(Map<String, String> param);
}
