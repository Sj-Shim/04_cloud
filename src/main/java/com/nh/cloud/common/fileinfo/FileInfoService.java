package com.nh.cloud.common.fileinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.cloud.common.consts.Consts;
import com.nh.cloud.common.fileinfo.mapper.FileInfoMapper;
import com.nh.cloud.common.log.SendLogInterface;
import com.nh.cloud.common.log.SendLogService;

@Service("fileInfoService")
public class FileInfoService implements SendLogInterface  {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	HashMap<String,String> logMap = new HashMap<String,String>();
	
	// 소스명
	String  pgmNm = "FileInfoService";
	
	@Autowired
    private FileInfoMapper  fileInfoMapper;
	
	@Autowired
	SendLogService          sendLogService;
	
	// 반입, 반출
	String snr_dsc = "";
	
	// 신규 파일 정보 생성
	public void insertFileInfo(HashMap<String, String> fileinfoMap)  throws Exception {
		
		logger.debug("FileInfoService insertFileInfo START ");
		logMap = new HashMap<String,String>();
		
		snr_dsc = fileinfoMap.get("snr_dsc");
		
		try {
			fileInfoMapper.insert(fileinfoMap);
		} catch (Exception e) {
			logger.error(sendLogService.printStackTrace(e));
			this.logMap.put("empno",     fileinfoMap.get("empno")+"");
			this.logMap.put("hstdsc",   "1"); 
			this.logMap.put("flnm",      fileinfoMap.get("fileName")+"");
    		
			logWrite(logMap, sendLogService.printStackTrace(e));
			
			logger.debug("@@@ FileInfoService insertFileInfo ERROR @@@ "+"["+fileinfoMap.get("fileName")+"]");
			
			throw new Exception();
		}
		
		logger.debug("FileInfoService insertFileInfo END ");
	}
	
	// 파일 정보 변경
	// SELECT 후 변경할지 고민
	public void updateFileInfo(HashMap<String, String> fileinfoMap)  throws Exception {
		
		logMap = new HashMap<String,String>();
		
		snr_dsc = fileinfoMap.get("snr_dsc");
		
		try {
			fileInfoMapper.update(fileinfoMap);
		} catch (Exception e) {
			
			logger.error(sendLogService.printStackTrace(e));
			
			this.logMap.put("empno",     fileinfoMap.get("empNo")+"");
			this.logMap.put("hstdsc",   "1"); 
			this.logMap.put("flNm",      fileinfoMap.get("fileName")+"");
    		
			logWrite(logMap, sendLogService.printStackTrace(e));
			
			throw new Exception();
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
    	logMap.put("snrdsc",      snr_dsc   );
  	    logger.debug("@@@ History Log @@@ == >> "+this.logMap);

		sendLogService.logWrite(logMap);
	}
	
	public List<Map<String, Object>> getPagedFiles(int page, int size, Map<String, Object> params){
		int offset = (page - 1) * size;
		params.put("offset", offset);
		params.put("size", size);
		
		return fileInfoMapper.selectPagedFiles(params);
	}
	
	public int countFiles(Map<String, Object> params) {
		return fileInfoMapper.countFiles(params);
	}
	
	public List<String> getXcrList() {
		return fileInfoMapper.selectAllXcrnm();
	}
	
	public Map<String, Object> getSelectedFile(Map<String, String> param){	
		return fileInfoMapper.getSelectedFileInfo(param);
	}
}
