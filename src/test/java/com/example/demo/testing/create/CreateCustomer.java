package com.example.demo.testing.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@SpringBootTest
public class CreateCustomer {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	public void test() {
		Customer c1 = new Customer();
		c1.setName("王曉明");
		
		Customer c2 = new Customer();
		c2.setName("陳曉東");
		
		Customer c3 = new Customer();
		c3.setName("張三");
		
		Customer c4 = new Customer();
		c4.setName("李四");
		
		Customer c5 = new Customer();
		c5.setName("'歐陽筷子");
		
		customerRepository.save(c1);
		customerRepository.save(c2);
		customerRepository.save(c3);
		customerRepository.save(c4);
		customerRepository.save(c5);
		
	}
	
	
}
