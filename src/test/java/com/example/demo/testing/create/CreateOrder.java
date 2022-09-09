package com.example.demo.testing.create;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

@SpringBootTest
public class CreateOrder {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void test() {
		// 資料預備
		// Sales 部門的員工 John 得到客戶 王曉明 的訂單 百合花 15 朵 與 鬱金香 12 朵
		Customer c1 = customerRepository.findById(1L).get();
		Employee e1 = employeeRepository.findById(1L).get();
		Product p1 = productRepository.findById(1L).get();
		Product p2 = productRepository.findById(2L).get();
		
		// 建立訂單
		Order order = new Order();
		order.setDate(new Date()); // 訂單日期
		// 配置關聯
		order.setCustomer(c1); // 配置客戶 王曉明
		order.setEmployee(e1); // 配置員工 John
		
		// 建立訂單明細 1
		OrderItem oi1 = new OrderItem();
		oi1.setAmount(15); // 購買數量
		// 配置關聯
		oi1.setOrder(order); // 配置訂購單(此訂購明細是隸屬於哪一個訂購單)
		oi1.setProduct(p1);  // 配置商品(此訂購明細要訂購的商品)
		
		// 建立訂單明細 2
		OrderItem oi2 = new OrderItem();
		oi2.setAmount(12); // 購買數量
		// 配置關聯
		oi2.setOrder(order); // 配置訂購單(此訂購明細是隸屬於哪一個訂購單)
		oi2.setProduct(p2);  // 配置商品(此訂購明細要訂購的商品)
		
		// 保存
		orderRepository.save(order);
		orderItemRepository.save(oi1);
		orderItemRepository.save(oi2);
	}
}








