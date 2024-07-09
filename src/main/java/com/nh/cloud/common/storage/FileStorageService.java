package com.nh.cloud.common.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nh.cloud.common.log.SendLogInterface;
import com.nh.cloud.common.log.SendLogService;
import com.nh.cloud.common.storage.exception.FileStorageException;
import com.nh.cloud.common.storage.exception.MyFileNotFoundException;

@Service("fileStorageService")
public class FileStorageService implements SendLogInterface {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SendLogService sendLogService;

    Path fileStorageLocation = null;//Paths.get(Consts.CarryInPath);

	public String storeFile(MultipartFile file, String storagePath) {
	
		fileStorageLocation= Paths.get(storagePath);
		
		logger.debug("uploadFileWithText==>>> "+storagePath);
		
		logger.debug("file.getOriginalFilename()1==>>> "+file.getOriginalFilename());
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		logger.debug("file.getOriginalFilename()2==>>> "+file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			
			logger.debug("file.getOriginalFilename()3==>>> "+file.getOriginalFilename());
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			
			logger.debug("file.getOriginalFilename()4==>>> "+file.getOriginalFilename());	
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			logger.debug("file.getOriginalFilename()5==>>> "+file.getOriginalFilename());
			return fileName;
			
		} catch (IOException ex) {
			
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName, String storagePath) {
		
		fileStorageLocation= Paths.get(storagePath);
		
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			
			Resource resource = new UrlResource(filePath.toUri());
			
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
	
	/**
	 * 로그와 이력을 저장하기 위한 Service
	 *
	 * @param  - logMap : system, logname, desc;
	 * @return - void
	 */
	public void logWrite(HashMap<String,String>  logMap) {

		sendLogService.logWrite(logMap);
	}

	@Override
	public void logWrite(HashMap<String, String> logMap, String desc) {
		// TODO Auto-generated method stub
		
	}
}