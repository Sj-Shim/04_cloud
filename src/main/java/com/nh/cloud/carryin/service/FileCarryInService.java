package com.nh.cloud.carryin.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nh.cloud.api.SendRestApiClient;
import com.nh.cloud.common.Utility;
import com.nh.cloud.common.blob.CloudBlobUploadService;
import com.nh.cloud.common.consts.Consts;
import com.nh.cloud.common.fileinfo.FileInfoService;
import com.nh.cloud.common.log.SendLogInterface;
import com.nh.cloud.common.log.SendLogService;
import com.nh.cloud.common.meta.FileNameMetaService;

@Service("FileCarryInService")
public class FileCarryInService  implements SendLogInterface {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	HashMap<String,String>  logMap = new HashMap<String,String>();
	
	@Autowired
	SendLogService         sendLogService;
	@Autowired
	FileNameMetaService    fileNameMetaService;
	@Autowired
	CloudBlobUploadService cloudBlobUploadService;
	@Autowired
	SendRestApiClient      sendRestApiClient;
	@Autowired
	FileInfoService        fileInfoService;
	@Autowired
	Utility                utility;
	
	// 소스명
	String  pgmnm          = "FileCarryInService";
	// 반입
	String  snrdsc         = "1";
	// 사번
	String  empno          = "";
	// 파일명
	String  orgfilename    = "";
	// 변경된  파일명
	String  uuidfilename   = "";
	
	@Transactional
	public void start(HashMap<String,Object> fileinfo)   throws Exception
	{
		logger.debug("@@@ Cloud [ APP 파일반입처리 START ] @@@");
		
    	HashMap<String,String>  valueMap = null;
		
    	try { 
			// fileinfo 내용의 parameter를 Parsing 한다. 
    		try { 
    		    for (String mapkey : fileinfo.keySet()) {
    			     valueMap =  (HashMap<String, String>)fileinfo.get(mapkey);
    			     break;
    		    }
    		} catch (Exception ex) {
    			  // 이 에러는 나올 수 없다. 그러나 체크해놓는다. 
    			  logger.debug("@@@ Cloud [ FileCarryInService fileinfo.keySet()  ERROR !!! ] @@@");
    			  logger.debug("@@@ Cloud [ CarryInReceiveFileService Map 생성부분 체크 바람  ERROR !!! ] @@@");
    			  logger.error(sendLogService.printStackTrace(ex));
    			  
    		      logMap.put("empno",   "ERROR");
    		      logMap.put("flnm",    "ERROR");
    		      logMap.put("snrdsc",  snrdsc);
    		      logMap.put("hstdsc",  "1");
    		      logWrite(logMap,      sendLogService.printStackTrace(ex));
    		      
    			  throw new Exception();
    		} 
    		
    		
    		empno         = valueMap.get("empno");
			orgfilename   = valueMap.get("orgfilename");
			uuidfilename  = valueMap.get("uuidfilename");

			// 처리 완료 반입 파일 정보
			insertCarryInFileInfo(orgfilename, uuidfilename);
			
			// 반입 파일 Upload
			blobUpload(uuidfilename, orgfilename);
			
			// 검역소 검증 API 호출
			apRestApiUploadCall(valueMap);

		} catch (Exception e) {

			logger.error(sendLogService.printStackTrace(e));
			
			logMap = new HashMap<String,String>();
	    	logMap.put("sysnm",  Consts.systemName );
	    	logMap.put("empno",  empno);
	    	logMap.put("flnm",   orgfilename);
	    	logMap.put("snrdsc", snrdsc);
	    	logMap.put("hstdsc", "1");
			logWrite(logMap,     sendLogService.printStackTrace(e)); 
			
			throw new Exception();
		}
		
    	// 정상적인 완료일 경우 이력 정보를 저장한다;
    	logMap = new HashMap<String,String>();
	    logMap.put("sysnm",  Consts.systemName );
	    logMap.put("empno",  empno);
	    logMap.put("flnm",   orgfilename);
	    logMap.put("snrdsc", snrdsc);
	    logMap.put("hstdsc", "0");
        logWrite(logMap,     "APP 반입  파일  처리  END");
    	
    	logger.debug("@@@ Cloud [ APP 파일반입처리 END ] @@@");
	}
	
	/**
	 * 반입 파일 정보 저장
	 * @param  - orgfilename : 원본 파일;
	 * @param  - uuidfilename: UUID 파일;
	 * @return - void
	 */
	public void insertCarryInFileInfo(String orgfilename, String uuidfilename)  throws Exception {
		
		logger.debug("@@@ Cloud [ APP 반입 파일 저장 START ] @@@");
		
		HashMap<String, String> fileinfoMap = new HashMap<String, String>();
		
		// 파일 확장명
		String ext = orgfilename.substring(orgfilename.lastIndexOf(".") + 1);
		
		try{
			fileinfoMap.put("sysnm",         Consts.systemName );
			fileinfoMap.put("snrdsc",        snrdsc   );
			fileinfoMap.put("empno",         empno);
			fileinfoMap.put("flstrgpathnm",  Consts.CarryInPath);
			fileinfoMap.put("flnm",          uuidfilename);
			fileinfoMap.put("otxtflnm",      orgfilename);
			fileinfoMap.put("xcrnm",         ext);
			fileinfoMap.put("hstdsc",        "0");
			
			fileInfoService.insertFileInfo(fileinfoMap);
			
		} catch (Exception e) {
        	logger.error(sendLogService.printStackTrace(e));
            throw new Exception();
		}
		
		logger.debug("@@@ Cloud [ APP 반입 파일 저장 뚱 ] @@@");
	}
	
	// 반입 파일 Blob Upload
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public void blobUpload(String originalFileName, String UUID_FileName)   throws Exception
	{
		//logMap = new HashMap<String,String>();
    	//logMap.put("flnm",    originalFileName);
    	//logMap.put("hstdsc",  "0");
    	//logWrite(logMap,      "Blob Upload Process START");
		// 여기서 Blob 파일에 대한 
		// CarryInWasPath or CarryInWasPath를 결정해야합니다
    	// 파일 이름이 변경됨
		try {
			cloudBlobUploadService.uploadProcess(UUID_FileName);
		} catch (Exception e) {
        	logger.error(sendLogService.printStackTrace(e));
            throw new Exception();
		}
	}
	// 반입 파일 AP Server RestApi 호출
	public void apRestApiUploadCall(HashMap<String,String>  valueMap)   throws Exception
	{
		logger.debug("@@@ Cloud [ AP Upload Api START ] @@@");
		
		logMap = new HashMap<String,String>();
	    logMap.put("hstdsc",   "0");
	    logMap.put("snrdsc",   "1");  // 반입 :1, 반출 :2
	    logMap.put("pgmnm",    pgmnm);
	    logMap.put("empno",    empno);
	    logMap.put("flnm",     orgfilename);
	    logWrite(logMap,       " AP Upload Api Call");
    	
		try {
			sendRestApiClient.upload(valueMap);
		} catch (Exception e) {
        	logger.error(sendLogService.printStackTrace(e));
            throw new Exception();
		}
		
		logger.debug("@@@ Cloud [ AP Upload Api END ] @@@");
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
    	logMap.put("empno",       empno);
    	logMap.put("pgmnm",       pgmnm);
    	logMap.put("flnm",        orgfilename);
  	    logger.debug("@@@ History Log @@@ == >> "+this.logMap);

		sendLogService.logWrite(logMap);
	}
}
