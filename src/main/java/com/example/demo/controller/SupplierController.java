package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("supplier", new Supplier());
		model.addAttribute("suppliers", supplierRepository.findAll());
		return "supplier"; 
	}
	
	@PostMapping(value = {"/", "/create"})
	public String create(Supplier supplier) {
		supplierRepository.save(supplier);
		return "redirect:/supplier/";
	}
	
	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit(@PathVariable("id") Long id, Model model) {
		Supplier supplier = supplierRepository.findById(id).get();
		model.addAttribute("supplier", supplier);
		return "supplier-edit";
	}
	
	@PutMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, Supplier supplier) {
		supplier.setId(id);
		supplierRepository.save(supplier);
		return "redirect:/supplier/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		supplierRepository.deleteById(id);
		return "redirect:/supplier/";
	}
}
