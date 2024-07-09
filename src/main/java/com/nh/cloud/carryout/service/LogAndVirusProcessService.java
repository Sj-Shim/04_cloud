package com.nh.cloud.carryout.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nh.cloud.common.fileinfo.FileInfoService;
import com.nh.cloud.common.log.SendLogService;

@Service("logAndVirusProcessService")
public class LogAndVirusProcessService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SendLogService         sendLogService;
	@Autowired
	FileInfoService        fileInfoService;
	
	// 반출
	String                 snr_dsc = "1";
	/**
	 * 반입 파일 Meta 처리.
	 * @param  - HashMap<String,String> logMap;
	 * @return - void
	 */
	public String logWrite(Object object)
	{
		logger.debug("@@@ Cloud [ 외부망 log 수신 처리 START ] @@@ "+object);
		
		HashMap<String,String>  logMap = null;
		
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> fieldMap = mapper.convertValue(object, new TypeReference<Map<String, Object>>() {});

        // Map -> JSON       
        JSONObject jsonObject = new JSONObject(fieldMap);
		//파싱할 데이터 저장
        String jsonStr = jsonObject.toJSONString();
        JSONParser jsonParser1 = new JSONParser();
		
        JSONObject jObject1;
		try {
			jObject1 = (JSONObject)jsonParser1.parse(jsonStr);
			
			JSONArray name = (JSONArray) jObject1.get("logWrite"); 
	        
	        System.out.println(name.get(0));
	        logMap = (HashMap<String, String>) name.get(0);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    try {
	    
	    	sendLogService.logWrite(logMap);
		
	    } catch (Exception e) {
			logger.error(sendLogService.printStackTrace(e));
		}
		
	    logger.debug("@@@ Cloud [ 외부망 log 수신 처리 END ] @@@ ");
	    
		return "OK";
	}
	
	/**
	 * 검역소 virusResult 처리.
	 * @param  - HashMap<String,String> map;
	 * @return - void
	 */
	public String virusResult(Object object)
	{
		logger.debug("@@@ Cloud [ 외부망 Virus Result 수신 ] @@@");
		
		HashMap<String,String> resultMap= (HashMap<String,String>) object;
		
		String retString = "";
		
		try{ 
			fileInfoService.updateFileInfo(resultMap);
		} catch (Exception e) {
			logger.error(sendLogService.printStackTrace(e));
		}
		
		return "OK";
	}
}
