package com.example.catchEx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
	
	@GetMapping("/html")
	public String html() {
		System.out.println("HTML 파일이 요청됨");
		return "redirect:hello.html";
		}
	
	@GetMapping("/jsp")
	public String jsp(Model model) {
		System.out.println("JSP 파일이 요청됨");
		model.addAttribute("username", "쌤즈");
		return "hello";
	}
	

}
