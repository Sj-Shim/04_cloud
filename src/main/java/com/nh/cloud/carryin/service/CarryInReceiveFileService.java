package com.nh.cloud.carryin.service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.cloud.common.consts.Consts;
import com.nh.cloud.common.log.SendLogInterface;
import com.nh.cloud.common.log.SendLogService;

@Service("carryInReceiveFileService")
public class CarryInReceiveFileService   implements SendLogInterface {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	HashMap<String,String>  logMap  = null;
	HashMap<String,String>  infoMap = null;
	
	// 소스명
	String  pgmnm   = "CarryInReceiveFileService";
	// 반입
	String  snrdsc = "1";
	
	@Autowired
	SendLogService sendLogService;
	
	// APP에서 수신된 자료를 API로 수신 받음
	public void receiveAppFile(String fileName, Object object) 
	{
		logger.debug("@@@ Cloud [ =====>>  수신 ] @@@ "+object);
		
		String  empno       = "";
		String  orgfilename = "";
		String  uuidfilename= "";
		String  UUID        = "";
		
		try{ 
			String strJson = object.toString();
	        //2. Parser
	        JSONParser jsonParser = new JSONParser();
	        //3. To Object
	        Object obj = jsonParser.parse(strJson);
	        //4. To JsonObject
	        JSONObject jsonObj = (JSONObject) obj;
	        
	        logger.debug("@@@ Cloud [ =====>>  수신 ] @@@ "+jsonObj.get("orgfilename"));
	        logger.debug("@@@ Cloud [ =====>>  수신 ] @@@ "+jsonObj.get("empno"));
       
			empno        = jsonObj.get("empno")+"";
			orgfilename  = jsonObj.get("orgfilename")+"";
			
			//## ================================ ##
	        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
			String nowTime = sd.format(System.currentTimeMillis());
			
		    byte[] bytesq = fileName.getBytes(StandardCharsets.UTF_8);
			String fileName_UTF_8 = new String(bytesq, StandardCharsets.UTF_8);
				
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				
			uuidfilename = java.util.UUID.randomUUID()+"";
			uuidfilename = uuidfilename.substring(0, 20);
			uuidfilename = uuidfilename+"_"+nowTime+"."+ext;
				
			UUID         = java.util.UUID.randomUUID()+""; 
			UUID         = UUID+"_"+nowTime+"."+ext;
				
			HashMap<String, Object> valueMap = new HashMap<String, Object>();
			HashMap<String, String> map      = new HashMap<String, String>();
			
			map.put("uuidfilename", uuidfilename);
			map.put("orgfilename",  orgfilename);
			map.put("empno",        empno);
			map.put("UUID",         UUID);
			valueMap.put(UUID,      map);
		
 		    //## ================================ ##
			logMap = new HashMap<String,String>();
			logMap.put("hstdsc",  "0");
			logMap.put("flnm",    orgfilename);
	    	logMap.put("empno",   empno);
	    	logWrite(logMap,      "APP File Main Process START");
			
	    	// 반입 처리를 위한 메모리 저장
	        Consts.setCarryInMap(valueMap);
	        
		} catch (Exception e) {
			logMap = new HashMap<String,String>();
        	logger.error(sendLogService.printStackTrace(e));
        	logMap.put("hstdsc",  "1");
        	logMap.put("flnm",    orgfilename);
        	logWrite(logMap, sendLogService.printStackTrace(e));
		}
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
    	logMap.put("snrdsc",      snrdsc   );
    	logMap.put("pgmnm",       pgmnm);
  	    logger.debug("@@@ History Log @@@ == >> "+this.logMap);

		sendLogService.logWrite(logMap);
	}
}
