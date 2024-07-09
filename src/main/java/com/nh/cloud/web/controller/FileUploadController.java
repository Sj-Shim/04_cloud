package com.nh.cloud.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
  
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
		  
    @GetMapping("/upload")
    public String UploadPage(Model model) {
	   return "uploadview";
    }
  
    @PostMapping("/upload")
    public ResponseEntity<String> upload(Model model, @RequestParam("files") MultipartFile[] files) {
    	HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain; charset=UTF-8");
    	StringBuilder fileNames = new StringBuilder();
	  
    	for (MultipartFile file : files) {
  
    		Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
    		fileNames.append(file.getOriginalFilename()+" ");
    		try {
    			Files.write(fileNameAndPath, file.getBytes());
    		} catch (IOException e) {
    			e.printStackTrace();
    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("파일 업로드 실패");
    		}
    		
	  }
	  
	  model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
	  
	  return ResponseEntity.ok().headers(headers).body("파일 업로드 성공");

    }
}
