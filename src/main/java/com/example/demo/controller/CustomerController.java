package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("customers", customerRepository.findAll());
		return "customer"; 
	}
	
	@PostMapping(value = {"/", "/create"})
	public String create(Customer customer) {
		customerRepository.save(customer);
		return "redirect:/customer/";
	}
	
	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit(@PathVariable("id") Long id, Model model) {
		Customer customer = customerRepository.findById(id).get();
		model.addAttribute("customer", customer);
		return "customer-edit";
	}
	
	@PutMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, Customer customer) {
		customer.setId(id);
		customerRepository.save(customer);
		return "redirect:/customer/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		customerRepository.deleteById(id);
		return "redirect:/customer/";
	}
}
