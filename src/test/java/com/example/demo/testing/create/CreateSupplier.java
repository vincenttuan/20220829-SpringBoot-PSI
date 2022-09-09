package com.example.demo.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@SpringBootTest
public class CreateSupplier {
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@Test
	public void test() {
		Supplier s1 = new Supplier();
		s1.setName("富貴芬芳");
		
		Supplier s2 = new Supplier();
		s2.setName("瑞士花園");
		
		Supplier s3 = new Supplier();
		s3.setName("伊莉莎白");
		
		supplierRepository.save(s1);
		supplierRepository.save(s2);
		supplierRepository.save(s3);
	}
	
}
