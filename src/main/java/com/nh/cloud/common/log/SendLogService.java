package com.nh.cloud.common.log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import com.nh.cloud.common.log.mapper.SendLogServiceMapper;

/**
 * 로그 이력 저장 Service 
 * <pre>
 * <b>History:</b>
 *    작성자, 1.0, 2024.06.26 최초작성
 * </pre>
 *
 * @author  김성현
 * @version 1.0, 2024.06.26 소스 수정
 * @see     None
 */

@Component
public class SendLogService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	HashMap<String,String>  logMap = new HashMap<String,String>();
	
	@Autowired
	SendLogServiceMapper  sendLogServiceMapper;
	
	// Exception을 String로 변환 해준다
	public String printStackTrace(Exception e) {         
	    
		String desc = "";
		
		try {
			ByteArrayOutputStream byteArrayOutputStream =   new ByteArrayOutputStream(); 
			PrintStream printStream                     =   new PrintStream(byteArrayOutputStream); 
			e.printStackTrace(printStream);
			desc = byteArrayOutputStream.toString(); 
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return desc;
	}
	
	/**
	 * 로그와 이력을 저장하기 위한 Service
	 *
	 * @param  - logMap;
	 * @return - void
	 */
	public void logWrite(HashMap<String, String> logMap) {
		
  	    logger.debug("@@@ @@@@@@@@  ====>>>> @@@@@@@@@@@@@@@@@ map "+logMap);
  	    logger.debug("@@@ @@@@@@@@  ====>>>> @@@@@@@@@@@@@@@@@ map "+logMap.get("obscntn"));
		
		try {
			sendLogServiceMapper.insert((HashMap<String, String>) logMap);
		} catch (Exception e) {
			logger.error(printStackTrace(e));
		}
	} 
}
