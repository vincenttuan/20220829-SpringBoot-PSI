package com.example.demo.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@SpringBootTest
public class CreateDepartment {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Test
	public void test() {
		Department d1 = new Department();
		d1.setName("Sales");
		
		Department d2 = new Department();
		d2.setName("IT");
		
		Department d3 = new Department();
		d3.setName("Purchase");
		
		departmentRepository.save(d1);
		departmentRepository.save(d2);
		departmentRepository.save(d3);
	}
}
