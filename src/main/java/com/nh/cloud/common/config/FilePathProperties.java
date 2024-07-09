package com.nh.cloud.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("file")
@Validated
public class FilePathProperties {

	//파일 반입 WAS 디렉토리
	private String CarryInPath;
	//Carry Out Upload 디렉토리
	private String CarryOutPath;
	
	public void setCarryInPath(String carryInPath) {
		CarryInPath = carryInPath;
	}
	
	public String getCarryInPath() {
		return CarryInPath;
	}
	
	public String getCarryOutPath() {
		return CarryOutPath;
	}
	
	public void setCarryOutPath(String carryOutPath) {
		CarryOutPath = carryOutPath;
	}
}
