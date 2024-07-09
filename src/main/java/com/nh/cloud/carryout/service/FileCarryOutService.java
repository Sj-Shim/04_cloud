package com.nh.cloud.carryout.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nh.cloud.common.Utility;
import com.nh.cloud.common.blob.CloudBlobUploadService;
import com.nh.cloud.common.consts.Consts;
import com.nh.cloud.common.fileinfo.FileInfoService;
import com.nh.cloud.common.log.SendLogInterface;
import com.nh.cloud.common.log.SendLogService;
import com.nh.cloud.common.meta.FileNameMetaService;

@Service("fileCarryOutService")
public class FileCarryOutService  implements SendLogInterface {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	HashMap<String,String>  logMap    =  null;
	
	@Autowired
	SendLogService          sendLogService;
	@Autowired
	CloudBlobUploadService  cloudBlobUploadService;
	@Autowired
	FileNameMetaService     fileNameMetaService;
	@Autowired
	FileInfoService         fileInfoService;
	@Autowired
	Utility                 utility;
	
	// 반출
	String  snr_dsc         = "2";
	// 사번
	String  empno           = "";
	// 파일명
	String  orgfilename     = "";
	// 변경된  파일명
	String  uuidfilename    = "";
	// 소스명
	String  pgmnm            = "FileCarryOutService";
	
	public void start(String FileName) 
	{
		logger.debug("@@@  Cloud 반출 파일  Process START@@@ ");
		
		HashMap<String,String>  retuenMap = utility.getFilename(FileName);
		
		empno         = retuenMap.get("empno");
		orgfilename   = retuenMap.get("orgfilename");
		uuidfilename  = retuenMap.get("uuidfilename");
		
		int errorChk = 0;
		
		logMap    = new HashMap<String,String>();
		
		logMap = new HashMap<String,String>();
    	logMap.put("hstdsc", "0"   );
    	logMap.put("empno",  empno );
    	logMap.put("flnm",   uuidfilename);
    	
    	logWrite(logMap, "Cloud 반출 파일  Process START");
		
		try{ 
			 //
			 azureBlobUploadProcess(uuidfilename);
		} catch (Exception e) {
			errorChk = 1;
			logger.error(sendLogService.printStackTrace(e));
		}
    	
		// 정상적인 완료일 경우 이력 정보를 저장한다;
    	if(errorChk == 0) {
    		logMap = new HashMap<String,String>();
        	logMap.put("hstdsc", "0"   );
        	logMap.put("empno",  empno );
        	logMap.put("flnm",   uuidfilename);
        	logWrite(logMap,     "Cloud 반출 파일  Process END");
    	}
		
		logger.debug("@@@ Cloud 반출 파일  Process END @@@");
	}
	
	@Transactional
	public void azureBlobUploadProcess(String FileName) throws Exception 
	{
		logger.debug("@@@ FileCarryOutService azureBlobUploadProcess START @@@");
		
		try{ 

			insertCloudFileInfo(orgfilename, uuidfilename);

			// cloudBlobUploadService.uploadProcess(UUID_FileName);
		} catch (Exception e) {
        	logger.error(sendLogService.printStackTrace(e));
            throw new Exception();
		}
		
		logger.debug("@@@ FileCarryOutService azureBlobUploadProcess END @@@");
	}
	/**
	 * 반입 파일 정보 생성
	 * @param  - orgfilename  :;
	 * @param  - uuidfilename :;
	 * @return - void
	 */
	public void insertCloudFileInfo(String  orgfilename, String uuidfilename)  throws Exception {
		
		logger.debug(" @@@ FileCarryOutService insertCloudFileInfo START @@@");
		
		HashMap<String, String> fileinfoMap = new HashMap<String, String>();
		
		// 파일 확장명
		String ext = orgfilename.substring(orgfilename.lastIndexOf(".") + 1);
		
		try{
			fileinfoMap.put("sysnm",         Consts.systemName );
			fileinfoMap.put("snrdsc",        snr_dsc   );
			fileinfoMap.put("empno",         empno);
			fileinfoMap.put("flstrgpathnm",  Consts.CarryOutPath);
			fileinfoMap.put("flnm",          uuidfilename);
			fileinfoMap.put("otxtflnm",      orgfilename);
			fileinfoMap.put("xcrnm",         ext);
			fileinfoMap.put("hstdsc",        "0");	
			
			fileInfoService.insertFileInfo(fileinfoMap);
			
		} catch (Exception e) {
        	logger.error(sendLogService.printStackTrace(e));
            throw new Exception();
		}
		
		logger.debug(" @@@ FileCarryOutService insertCloudFileInfo END @@@");
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
    	logMap.put("snrdsc",      snr_dsc   );
    	logMap.put("empno",       empno);
    	logMap.put("pgmnm",       pgmnm);
  	    logger.debug("@@@ History Log @@@ == >> "+this.logMap);
		
  	    sendLogService.logWrite(logMap);
	}
}
