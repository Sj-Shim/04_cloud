package com.nh.cloud.api;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nh.cloud.common.consts.Consts;
import com.nh.cloud.common.log.SendLogService;

@Component
public class SendRestApiClient  {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	HashMap<String,String>  logMap = new HashMap<String,String>();
	
	// 소스명
	String  pgmnm   = "SendRestApiClient";
	
	@Autowired
	SendLogService sendLogService;
	/**
	 * AP Upload Api 호출.
	 * response로 송신 결과를 수신한다.
	 * @param  - valueMap : APP에서 받은 메타 정보들.... ; 
	 * @return - void
	 */
	public void upload(HashMap<String,String>  valueMap) throws Exception {
		
		logger.debug("@@@ Cloud [ AP Upload Api 호출 Http Client START ] @@@");
		
		// 사번
		String  empno = "";
		String  orgfilename = "";
		
		try {
			
			empno         = valueMap.get("empno");
			orgfilename   = valueMap.get("orgfilename");
			
			Path testF = Paths.get(Consts.CarryInPath.toString()+File.separator+orgfilename);
			
			String serverUrl = "http://localhost:8889/cloudUpload";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", new FileSystemResource(testF.toFile()));
			body.add("info", valueMap);

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
			
			System.out.println(response);
			
			if( ! response.getStatusCode().toString().equalsIgnoreCase("200")) {
				logMap = new HashMap<String,String>();
			    logMap.put("hstdsc",  "1");
			    logMap.put("snrdsc",  "1");  // 반입 :1, 반출 :2
			    logMap.put("pgmnm",    pgmnm);
			    logMap.put("empno",    empno);
			    logMap.put("flnm",     orgfilename);
			    logWrite(logMap,       " AP 서버간 송수신 전문  에러");
	            throw new Exception();
			}
		} catch (Exception e) {
			logger.error(sendLogService.printStackTrace(e));
			logMap = new HashMap<String,String>();
		    logMap.put("hstdsc",  "1");
		    logMap.put("snrdsc",  "1");  // 반입 :1, 반출 :2
		    logMap.put("pgmnm",    pgmnm);
		    logMap.put("empno",    empno);
		    logMap.put("flnm",     orgfilename);
		    logWrite(logMap,       " AP 서버간 송수신 에러 ");
            throw new Exception();
        }
		
		logMap = new HashMap<String,String>();
	    logMap.put("hstdsc",  "0");
	    logMap.put("snrdsc",  "1");  // 반입 :1, 반출 :2
	    logMap.put("pgmnm",    pgmnm);
	    logMap.put("empno",    empno);
	    logMap.put("flnm",     orgfilename);
	    logWrite(logMap,       " AP 서버 송신 완료 ");
	    
	    logger.debug("@@@ Cloud [ AP Upload Api 호출 Http Client END ] @@@");
	}
		
	/**
	 * 로그와 이력을 저장을 위해  SendLogService logWrite() 호출
	 *
	 * @param  - logMap
	 * @return - void
	 */
	public void logWrite(HashMap<String,String>  paramMap, String desc) {
			
	    	logMap = paramMap;
	    	
	    	if(desc.length() < 98)    this.logMap.put("obscntn", desc);
	    	else                      this.logMap.put("obscntn", desc.substring(0, 95));
	    	logMap.put("sysnm",       Consts.systemName );
	    	logMap.put("pgmnm",       pgmnm);
	  	    logger.debug("@@@ History Log @@@ == >> "+this.logMap);

			sendLogService.logWrite(logMap);
	}
}
