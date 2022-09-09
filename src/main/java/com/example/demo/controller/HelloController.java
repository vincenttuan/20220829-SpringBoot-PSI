package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	// 執行網址: /psi/hello/
	@RequestMapping("/") 
	public String index() {
		return "hello";
	}
	
}
