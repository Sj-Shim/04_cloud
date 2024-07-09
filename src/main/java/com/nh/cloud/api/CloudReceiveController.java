package com.nh.cloud.api;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nh.cloud.carryin.service.CarryInReceiveFileService;
import com.nh.cloud.carryout.service.CarryOutReceiveService;
import com.nh.cloud.carryout.service.LogAndVirusProcessService;
import com.nh.cloud.common.consts.Consts;
import com.nh.cloud.common.storage.FileStorageService;

/**
 * DMZ_AP 서버로부터 반입파일을 수신 받는다. 
 * RestAPI 제공
 * <pre>
 * <b>History:</b>
 *    작성자, 1.0, 2024.06.26 최초작성
 * </pre>
 *
 * @author  김성현
 * @version 1.0, 2024.06.26 소스 수정
 * @see     None
 */

@RestController
public class CloudReceiveController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	CarryInReceiveFileService  carryInReceiveFileService;
	
	@Autowired
	LogAndVirusProcessService  logAndVirusProcessService;
	
	@Autowired
	CarryOutReceiveService     carryOutReceiveService;

	@RequestMapping(value = "/cloudUpload", method = RequestMethod.POST)
	public Object upload(@RequestParam("file") MultipartFile file, @RequestParam("info") Object object) throws Exception {
		
		logger.debug("@@@ CloudCarryInMorningListener START @@@ ");
		
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		String  fileName = fileStorageService.storeFile(file, Consts.CarryInPath);
				
		String  fileDownloadUri = Consts.CarryInPath;
	
		logger.debug("@@@@@ =======>>>>> "+fileName);
		logger.debug("@@@@@ =======>>>>> "+fileDownloadUri);
		
		// 동기화로해야하는지 고민
		carryInReceiveFileService.receiveAppFile(fileName,object);
		
		return returnMap;
	}
	// 비동기식으로 받음
	// 처리 결과까지 외부망에 알리지 않는다.
	// 저장된것 까지만..............
	@RequestMapping(value = "/outnetUpload", method = RequestMethod.POST)
	public Object outnetUpload(@RequestParam("file") MultipartFile file) throws Exception {
		
		logger.debug("@@@ CloudCarryInMorningListener START @@@ ");
		
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		
		String  fileName = fileStorageService.storeFile(file, Consts.CarryOutPath);
				
		String  fileDownloadUri = Consts.CarryOutPath;
		
		logger.debug("@@@@@ =======>>>>> "+fileName);
		logger.debug("@@@@@ =======>>>>> "+fileDownloadUri);
		
		carryOutReceiveService.receiveOutnetFile(fileName);
				
		return returnMap;
		
	}
    @RequestMapping  (value = "/logWrite", method = RequestMethod.POST)
    @ResponseBody
    public String logWrite(@RequestBody Object object) {
         
    	 logger.debug("@ logWritelogWritelogWritelogWritelogWrite");
         logger.debug("@ params=>   "+"["+object+"]");

         return logAndVirusProcessService.logWrite(object);
    }
    
    @RequestMapping  (value = "/virusResult", method = RequestMethod.POST)
    @ResponseBody
    public String virusResult(@RequestBody Object object) {
	     
	   	 logger.debug("@ virusResultvirusResultvirusResultvirusResultvirusResult");
	     logger.debug("@ params=>   "+"["+object+"]");
	        
	     return logAndVirusProcessService.virusResult(object);
    }
}
