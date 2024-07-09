package com.nh.cloud.common;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class Utility {

	/**
	 * 로그와 이력을 저장을 위해  SendLogService logWrite() 호출
	 *
	 * @param  - logMap
	 * @return - void
	 */
	public HashMap<String,String> getFilename(String filename) {
		
		HashMap<String,String>  retuenMap = new HashMap<String,String>();

		try { 
			String[] result = filename.split("@");
			retuenMap.put("empno",            result[0]);
			retuenMap.put("orgfilename",      result[1]);
			retuenMap.put("uuidfilename",     filename);
	
		} catch (Exception e) {
        }
		
		return retuenMap;
	}
}
