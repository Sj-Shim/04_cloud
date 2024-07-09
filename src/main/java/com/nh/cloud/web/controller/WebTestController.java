package com.nh.cloud.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nh.cloud.common.fileinfo.FileInfoService;
import com.nh.cloud.web.service.ReadLogService;

@Controller
public class WebTestController {
	
	@Autowired
	private FileInfoService fileInfoService;
	
	@Autowired
	private ReadLogService readLogService;
	
	@RequestMapping("/")
	public String getIndex() {
		return "redirect:/files";
	}

    @RequestMapping("/files")
    public String getHomePage(
    		@RequestParam(defaultValue="1") int page,
    		@RequestParam(defaultValue="10") int size,
    		@RequestParam(required = false) String s,
    		@RequestParam(required = false) String e,
    		@RequestParam(required = false) String ext,
    		@RequestParam(required = false) String n,
    		@RequestParam(required = false) String id,
    		Model model) {

    	Map<String, Object> params = new HashMap<>();
    	params.put("st_dt", s);
    	params.put("ed_dt", e);
    	params.put("ext", ext);
    	params.put("file_name", n);
    	params.put("id", id);
    	
    	List<Map<String, Object>> files = fileInfoService.getPagedFiles(page, size, params);
    	int totalFiles = fileInfoService.countFiles(params);
    	int totalPages = (int) Math.ceil((double) ((int) Math.max(totalFiles , 1)) / size );
       	
        int pageGroup = (page - 1) / 10;
        int startPage = pageGroup * 10 + 1;
        int endPage = Math.min(startPage + 9, totalPages);
        
        List<String> xcrList = new ArrayList<>();
        xcrList = fileInfoService.getXcrList();
        
        model.addAttribute("file_name", n);
        model.addAttribute("st_dt", s);
        model.addAttribute("ed_dt", e);
        model.addAttribute("ext", ext);
        model.addAttribute("usr_id", id);
        model.addAttribute("urlPath", "files");
        model.addAttribute("xcrList", xcrList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    	model.addAttribute("totalCnt", totalFiles);
    	model.addAttribute("currentPage", page);
    	model.addAttribute("files", files);
    	model.addAttribute("totalPages", totalPages);
    	
        return "index";
    }
        
    @RequestMapping("/trans_hist")
    public String getHistoryPage(
    		@RequestParam(defaultValue="1") int page,
    		@RequestParam(defaultValue="10") int size,
    		@RequestParam(required = false) String s,
    		@RequestParam(required = false) String e,
    		@RequestParam(required = false) String h,
    		@RequestParam(required = false) String n,
    		@RequestParam(required = false) String id,
    		Model model) {
    	Map<String, Object> params = new HashMap<>();
    	
    	params.put("id", id);
    	params.put("st_dt", s);
    	params.put("ed_dt", e);
    	params.put("hst_dsc", h);
    	params.put("file_name", n);
    	List<Map<String, Object>> histories = readLogService.selectFilteredLog(page, size, params);
    	int totalHistory = readLogService.countHistories(params); 
    	int totalPages = (int) Math.ceil((double) ((int) Math.max(totalHistory, 1)) / size );
       	
        int pageGroup = (page - 1) / 10;
        int startPage = pageGroup * 10 + 1;
        int endPage = Math.min(startPage + 9, totalPages);
                
        model.addAttribute("file_name", n);
        model.addAttribute("st_dt", s);
        model.addAttribute("ed_dt", e);
        model.addAttribute("hst_dsc", h);
        model.addAttribute("usr_id", id);
    	model.addAttribute("urlPath", "hist");
    	model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    	model.addAttribute("currentPage", page);
    	model.addAttribute("histories", histories);
    	model.addAttribute("totalPages", totalPages);
    	
    	return "history";
    }
    
    @GetMapping("/user_mng")
    public String getMngPage(
    		@RequestParam(defaultValue="1") int page,
    		@RequestParam(defaultValue="10") int size,
    		@RequestParam(required = false) String usrnm,
    		Model model) {
    	
    	int totalPages = 1;
    	int pageGroup = (page - 1) / 10;
        int startPage = pageGroup * 10 + 1;
        int endPage = Math.min(startPage + 9, totalPages);
        
        model.addAttribute("totalCnt", 0);
        
        model.addAttribute("usrnm", usrnm);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    	model.addAttribute("currentPage", page);
    	model.addAttribute("urlPath", "mng");
    	model.addAttribute("totalPages", totalPages);
    	return "managerList";
    }
    
    @GetMapping("/user_mng/addmanager")
    public String getSubordinateList(
    		@RequestParam(defaultValue="1") int page,
    		@RequestParam(defaultValue="10") int size,
    		@RequestParam(required = false) String usrnm,
    		Model model) {
    	
    	int totalPages = 1;
    	int pageGroup = (page - 1) / 10;
        int startPage = pageGroup * 10 + 1;
        int endPage = Math.min(startPage + 9, totalPages);

        List<Map<String, Object>> params = new ArrayList<>();
        Map<String, Object> param1 = new HashMap<>();
        param1.put("empno", "P0001111");
        param1.put("name", "테스터1");
        param1.put("dept_name", "개발");
        param1.put("bsnm", "개발테스트");
        param1.put("pzcnm", "대리");
        params.add(param1);
        
        Map<String, Object> param2 = new HashMap<>();
        param2.put("empno", "P0002222");
        param2.put("name", "테스터2");
        param2.put("dept_name", "테스트");
        param2.put("bsnm", "개발테스트");
        param2.put("pzcnm", "계장");
        params.add(param2);
        
        model.addAttribute("totalCnt", params.size());
        
        model.addAttribute("staffList", params);
        model.addAttribute("usrnm", usrnm);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    	model.addAttribute("currentPage", page);
    	model.addAttribute("urlPath", "mng");
    	model.addAttribute("totalPages", totalPages);
    	
    	return "userManageForm";
    }
    
}
