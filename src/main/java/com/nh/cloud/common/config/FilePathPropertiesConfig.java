package com.nh.cloud.common.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nh.cloud.common.consts.Consts;

@Component("filePathPropertiesConfig")
public class FilePathPropertiesConfig {

	@Autowired
	FilePathProperties filePathProperties;
	
	@PostConstruct
	public void filePathPropertiesConfig() {
		//파일 반출 대상 디렉토리
		Consts.CarryOutPath        = filePathProperties.getCarryOutPath();
		//WEB 작업 Upload 디렉토리
		Consts.CarryInPath         = filePathProperties.getCarryInPath();
	}
}
