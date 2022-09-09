package com.example.demo.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class CreateProduct {
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void test() {
		Product p1 = new Product();
		p1.setName("百合花");
		p1.setCost(10);
		p1.setPrice(40);
		
		Product p2 = new Product();
		p2.setName("鬱金香");
		p2.setCost(15);
		p2.setPrice(70);
		
		Product p3 = new Product();
		p3.setName("玫瑰");
		p3.setCost(25);
		p3.setPrice(150);
		
		productRepository.save(p1);
		productRepository.save(p2);
		productRepository.save(p3);
	}
	
}
