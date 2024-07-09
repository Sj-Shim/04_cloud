package com.nh.cloud.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nh.cloud.common.fileinfo.FileInfoService;
import com.nh.cloud.web.service.UserInfoService;

@RestController
public class ApiController {

	@Autowired
	private FileInfoService fileInfoService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/api/file_info")
    public Map<String, Object> getFileInfo(@RequestBody Map<String, String> params) {
        return fileInfoService.getSelectedFile(params);
    }
	
	@PostMapping("/api/file/download")
	public ResponseEntity<Resource> downloadFile(@RequestBody Map<String, String> params, HttpServletResponse response){
		
		try {
			Map<String, Object> fileInfo = fileInfoService.getSelectedFile(params);
			Path filePath = Paths.get(fileInfo.get("pathnm").toString(), fileInfo.get("flnm").toString());
			File file = filePath.toFile();
			
			if(!file.exists()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			
			String downloadFileName = fileInfo.containsKey("otxt_flnm") ? fileInfo.get("otxt_flnm").toString() : fileInfo.get("flnm").toString();

			String contentType = Files.probeContentType(filePath);
			if(contentType == null) {
				contentType = "application/octet-stream";
			}
			
			long fileSize = file.length();
			
			response.setContentType(contentType);
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadFileName + "\"");
			response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSize));
			
			OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            fis.close();
            os.flush();
            return ResponseEntity.ok().body(new FileSystemResource(file));
		}
		catch(IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping("/api/user_mng/add")
	public ResponseEntity<String> addUserToManager(@RequestBody Map<String, String> params){
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain; charset=UTF-8");

		try {
			userInfoService.addUserToManager(params);
			return ResponseEntity.ok().headers(headers).body("관리자 추가 성공");
		}
		catch(PersistenceException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("관리자 등록 중 에러가 발생하였습니다.");
		}
	}
	
	@PostMapping("/api/user_mng/expire")
	public ResponseEntity<String> expireManager(@RequestBody Map<String, String> params){
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain; charset=UTF-8");
		try {
			userInfoService.expireManager(params);
			return ResponseEntity.ok().headers(headers).body("관리자 만료 처리 완료");
		}
		catch(PersistenceException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("관리자 만료 처리 중 에러가 발생하였습니다.");
		}
	}
}
