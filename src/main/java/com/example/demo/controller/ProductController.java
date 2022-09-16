package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.LineNotifyService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private LineNotifyService lineNotifyService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("products", productRepository.findAll());
		return "product"; 
	}
	
	@PostMapping(value = {"/", "/create"})
	public String create(Product product) {
		productRepository.save(product);
		return "redirect:/product/";
	}
	
	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit(@PathVariable("id") Long id, Model model) {
		Product product = productRepository.findById(id).get();
		model.addAttribute("product", product);
		return "product-edit";
	}
	
	@PutMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, Product product) {
		product.setId(id);
		productRepository.save(product);
		return "redirect:/product/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		productRepository.deleteById(id);
		return "redirect:/product/";
	}
	
	@GetMapping("/linenotify/{id}")
	public String lineNotify(@PathVariable("id") Long id) {
		Product product = productRepository.findById(id).get();
		// 傳送 Line
		try {
			//lineNotifyService.send(product);
			lineNotifyService.send(product, "rose.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/product/";
	}
	
}
