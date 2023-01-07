package com.example.catchEx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
	@GetMapping({"", "/"})
	public String getPostList() {
		return "index";
	}

}
