package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("department", new Department());
		model.addAttribute("departments", departmentRepository.findAll());
		return "department"; 
	}
	
	@PostMapping(value = {"/", "/create"})
	public String create(Department department) {
		departmentRepository.save(department);
		return "redirect:/department/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Department department = departmentRepository.findById(id).get();
		model.addAttribute("department", department);
		return "department-edit";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, Department department) {
		department.setId(id);
		departmentRepository.save(department);
		return "redirect:/department/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		departmentRepository.deleteById(id);
		return "redirect:/department/";
	}
}
