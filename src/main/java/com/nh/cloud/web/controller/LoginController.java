package com.nh.cloud.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
		
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String doLogin(
			HttpServletRequest request,
			RedirectAttributes reDi
			) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isAdmin = "on".equals(request.getParameter("admin"));
		System.out.println("username : " +username + "\npassword : " + password + "\nadmin : " + isAdmin);
		if(/*!xxService.getAgrmYn() &&*/ true) {
			reDi.addFlashAttribute("username", username);
			return  "redirect:/login/agreement";
		}
		
		return "redirect:/files";
	}
	
	@GetMapping("/login/agreement")
	public String agreementForm(Model model) {
		String username = (String) model.asMap().get("username");
		if(username == null) return "redirect:/login";
		System.out.println("agreement username = " + username);
		model.addAttribute("blg_dsc", 2);
		model.addAttribute("username", username);
		return "agreementForm";
	}
	
	@PostMapping("/login/agreement")
	public String submitAgreement(
			@RequestParam String agree_col,
			@RequestParam String agree_pass,
			@RequestParam String username,
			Model model) {
		
		System.out.println("agree_col :" + agree_col + "\nagree_pass : " + agree_pass + "\nusername : " + username);
		
		return "redirect:/";
	}
	
}
