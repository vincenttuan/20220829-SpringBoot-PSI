package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	@RequestMapping("/")
	public String index(Model model) {
		return "inventory";
	}
	
}
