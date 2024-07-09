package com.nh.cloud.common.meta;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.cloud.common.consts.Consts;
import com.nh.cloud.common.log.SendLogService;

@Service("fileNameMetaService")
public class FileNameMetaService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	HashMap<String,String>  logMap = new HashMap<String,String>();
	
	// 소스명
	String  pgmnm   = "fileNameMetaService";
	// 1,반입   2,반출
	String  snr_dsc = "";
	// 사번 
	String  empno   = "default";
	
	@Autowired
	SendLogService   sendLogService;
	
	
	
	/**
	 * 파일명를 File에 넣고 UUID를 파일명으로 rename 한다.
	 * @param  - fileName 파일명;
	 * @param  - fileName 파일명;
	 * @param  - fileName 파일명;
	 * @return - void
	 */
	
	public String setMetaFileName(HashMap<String,String>  map, String pathName, String fileName)  throws Exception
	{
		// 반출
		snr_dsc = "1";
		
		String newFileValue = "";
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowTime = sd.format(System.currentTimeMillis());
		
		try {
			// 파일명 변경
	        byte[] bytesq = fileName.getBytes(StandardCharsets.UTF_8);
			String fileName_UTF_8 = new String(bytesq, StandardCharsets.UTF_8);
			
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			newFileValue = java.util.UUID.randomUUID()+"";
			newFileValue = newFileValue.substring(0, 20);
			newFileValue = "_"+nowTime+"."+ext;
			newFileValue = newFileValue+"@"+fileName+"@"+map.get("empno")+"";
			
			// 변경될 파일명
            //newFileValue = java.util.UUID.randomUUID()+"_"+nowTime+"."+ext;
	        
	        File oldFileName    = new File(pathName+File.separator+fileName_UTF_8);         
	        File newFileName    = new File(pathName+File.separator+newFileValue);
	        
	        boolean result = oldFileName.renameTo(newFileName);
	        
	        logger.debug("@@@ newFileNamenewFileNamenewFileNamenewFileNamenewFileName @@@ "+newFileName);
	        logger.debug("@@@ newFileNamenewFileNamenewFileNamenewFileNamenewFileName @@@ "+newFileName);
	        
	        // 파일 Move가 안된다
	        if(! result) {
				logMap = map;
				// Batch File MOVE 종료 이력
		    	logMap.put("hstdsc",  "1");
		    	logMap.put("flnm",    fileName);
		    	logWrite(logMap,      fileName+"UUID Change ERROR @@@@@");
	        	
	        	throw new Exception();
	        }
		} catch (Exception e) {
			logger.error(sendLogService.printStackTrace(e));
			logMap = map;
	    	logMap.put("hstdsc",  "1");
	    	logMap.put("flnm",    fileName);
	    	logWrite(logMap,      fileName+"UUID Change ERROR @@@@@");
            
            throw new Exception();
        }
		
		return newFileValue;
	}
	
	/**
	 * 파일명를 File에 넣고 UUID를 파일명으로 rename 한다.
	 * @param  - fileName 파일명;
	 * @param  - fileName 파일명;
	 * @param  - fileName 파일명;
	 * @return - void
	 */
	/*
	public String setMetaFileName(HashMap<String,String>  paramlogMap, String pathName, String fileName)  throws Exception
	{
		String newFileValue = "";
		snr_dsc = paramlogMap.get("snrdsc");
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowTime = sd.format(System.currentTimeMillis());
		
		try {
			// 파일명 변경
	        byte[] bytesq = fileName.getBytes(StandardCharsets.UTF_8);
			String fileName_UTF_8 = new String(bytesq, StandardCharsets.UTF_8);
			
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			// 변경될 파일명
            newFileValue = java.util.UUID.randomUUID()+"_"+nowTime+"."+ext;
	        
	        File oldFileName    = new File(pathName+File.separator+fileName_UTF_8);         
	        File newFileName    = new File(pathName+File.separator+newFileValue);
	        
	        boolean result = oldFileName.renameTo(newFileName);
	        
	        // 파일 Move가 안된다
	        if(! result) {
				logMap = paramlogMap;
				// Batch File MOVE 종료 이력
		    	this.logMap.put("hstdsc",  "1");
		    	this.logMap.put("flnm",    fileName);
		    	historyLog(logMap, fileName+"UUID Change ERROR @@@@@");
	        	
	        	throw new Exception();
	        }
			
            // 파일에 사용자 정의 속성 추가
	        Path path = Paths.get(pathName+File.separator+newFileValue);
	        
            UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
            
            // 속성을 추가하려는 값 설정
            String attributeName  = Consts.attributeName;
            String attributeValue = fileName_UTF_8;
            
            System.out.println(attributeName);
            System.out.println(attributeValue);
            
            byte[] value = attributeValue.getBytes("UTF-8");
            
            ByteBuffer buffer = ByteBuffer.wrap(value);
            // 속성 추가
            view.write(attributeName, buffer);
		} catch (IOException e) {
			
			logger.error(sendLogService.printStackTrace(e));
			logMap = paramlogMap;
			// Batch File MOVE 종료 이력
	    	this.logMap.put("hstdsc",  "1");
	    	this.logMap.put("flnm",    fileName);
	    	historyLog(logMap, fileName+"UUID Change ERROR @@@@@");
            
            throw new Exception();
        }
		
		return newFileValue;
	} */
	
	/**
	 * UUID_FileName 파일명의 속성을 얻는다.
	 * @param  - UUID_FileName 파일명; 
	 * @param  - snrdsc; 1=반입 , 2=반출
	 * @return - void
	 */
	public HashMap<String,String> getMetaData(String UUID_FileName, String snrdsc) throws Exception
	{
		HashMap<String,String>  retuenMap = new HashMap<String,String>();
		
		snr_dsc = snrdsc;
		
		String sftpPath = "";
		
		try { 
			
			if(snrdsc.equals("1")) sftpPath = Consts.CarryInPath;
			else                   sftpPath = Consts.CarryOutPath;
	        
			retuenMap = readMetaData(UUID_FileName, sftpPath);
		
		} catch (Exception e) {
			logger.error(sendLogService.printStackTrace(e));
			throw new Exception();
        }
		
		return retuenMap;
	}
	
	/**
	 * UUID_FileName 파일명의 속성을 얻는다.
	 * @param  - UUID_FileName 파일명; 
	 * @param  - sftpPath; 파일위치
	 * @return - void
	 */
	// ============================================================================ //
	public HashMap<String,String> readMetaData(String UUID_FileName, String sftpPath) throws Exception
	{
		HashMap<String,String>  retuenMap = new HashMap<String,String>();
		
		String attributeName    = Consts.attributeName;
		String attributeEmpno   = Consts.attributeEmpno;
		String originalFileName = "";

		
		logMap = new HashMap<String,String>();
		
		// SFTP INIT
    	logMap.put("hstdsc",   "0");
    	logWrite(logMap, " getMetaData START ");
		
		try {
			logger.debug("1111===>>> "+UUID_FileName);
	        // 파일 저장 디렉토리
	        Path path = Paths.get(sftpPath+File.separator+UUID_FileName);
	        logger.debug("2222===>>> "+UUID_FileName);
	        UserDefinedFileAttributeView view = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
	        logger.debug("3333===>>> "+UUID_FileName);
	        // 속성 읽기
	        // ****************************************************************//
	        logger.debug("4444===>>> "+attributeName);
	        try {
		        ByteBuffer fNameBuffer = ByteBuffer.allocate(view.size(attributeName));
		        view.read(attributeName, fNameBuffer);
		        fNameBuffer.flip();
		        originalFileName = new String(fNameBuffer.array(), "UTF-8");
		        logger.debug("6666===>>> "+originalFileName);
		        retuenMap.put(attributeName, originalFileName);
	        } catch (Exception e) {logger.error(sendLogService.printStackTrace(e));}
	        try {
	            ByteBuffer empnoBuffer = ByteBuffer.allocate(view.size(attributeEmpno));
	            view.read(attributeEmpno, empnoBuffer);
	            empnoBuffer.flip();
	            empno = new String(empnoBuffer.array(), "UTF-8");
	            retuenMap.put(attributeEmpno, empno);
	        } catch (Exception e) {logger.error(sendLogService.printStackTrace(e));}
	        /*
	        ByteBuffer readBuffer = ByteBuffer.allocate(view.size(attributeName));
	        view.read(attributeName, readBuffer);
	        readBuffer.flip();
	        originalFileName = new String(readBuffer.array(), "UTF-8");
	        */
	        logger.debug("5555===>>> "+UUID_FileName);
	        
		} catch (Exception e) {
			logger.error(sendLogService.printStackTrace(e));
			logMap = new HashMap<String,String>();
			logMap.put("hstdsc",   "1");
	    	logMap.put("flnm",     UUID_FileName);
	    	logWrite(logMap, "   getMetaData END [FileName] "+originalFileName);
			throw new Exception();
        }
		
		// SFTP INIT
    	logMap.put("hstdsc",   "0");
    	logMap.put("flnm",     originalFileName+"###"+UUID_FileName);
    	logMap.put("empno",    empno);
    	logWrite(logMap, "   getMetaData END [FileName] "+originalFileName);
    	
        return retuenMap;
	}

	/**
	 * 로그와 이력을 저장
	 *
	 * @param  - logMap, desc;
	 * @return - void
	 */
    public void logWrite(HashMap<String, String> paramMap, String desc) {
    	
    	logMap = paramMap;
    	
    	if(desc.length() < 98) logMap.put("obscntn", desc);
    	else                   logMap.put("obscntn", desc.substring(0, 95));
    	logMap.put("sysnm",    Consts.systemName );
    	logMap.put("snrdsc",   snr_dsc   );
    	logMap.put("pgmnm",    pgmnm);
    	logMap.put("empno",    empno);
  	    logger.debug("@@@ historyOrError MAP @@@ == >> "+this.logMap);
        
  	    sendLogService.logWrite(logMap);
    } 
}
