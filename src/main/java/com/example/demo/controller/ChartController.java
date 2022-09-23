package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.view.ProductSales;
import com.example.demo.repository.ProductRepository;

@Controller
@RequestMapping("/chart")
public class ChartController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		List<ProductSales> productSales = productRepository.queryProductSales();
		model.addAttribute("productSales", productSales);
		// 組合 google chart 要的資料格式
		String chartValues = "";
		for(ProductSales ps : productSales) {
			//chartValues += "[\'" + ps.getName() + "\'," + ps.getTotal() + "],";
			chartValues += "[" + ps.getId() + "," + ps.getTotal() + "],";
		}
		model.addAttribute("chartValues", chartValues);
		return "chart"; 
	}
	
}
