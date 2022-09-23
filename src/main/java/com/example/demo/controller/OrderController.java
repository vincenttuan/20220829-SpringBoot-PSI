package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Customer;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.validator.InventoryValidator;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.CustomerRepository;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private InventoryValidator inventoryValidator;
	
	@GetMapping("/")
	public String index(Model model) {
		Order order = new Order();
		List<Order> orders = orderRepository.findAll();
		List<Customer> customers = customerRepository.findAll();
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("order", order);
		model.addAttribute("orders", orders);
		model.addAttribute("customers", customers);
		model.addAttribute("employees", employees);
		return "order";
	}
	
	@PostMapping("/")
	public String create(Order order) {
		orderRepository.save(order);
		return "redirect:/order/";
	}
	
	@GetMapping("/edit/{id}") // 修改頁面的呈現
	public String edit(@PathVariable("id") Long id, Model model) {
		Order order = orderRepository.findById(id).get();
		List<Customer> customers = customerRepository.findAll();
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("order", order);
		model.addAttribute("customers", customers);
		model.addAttribute("employees", employees);
		return "order-edit";
	}
	
	@PutMapping("/{id}") // 對資料庫進行修改
	public String update(@PathVariable("id") Long id, Order order) {
		order.setId(id);
		orderRepository.save(order);
		return "redirect:/order/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		orderRepository.deleteById(id);
		return "redirect:/order/";
	}
	
	// 檢視訂單明細
	// oid -> 訂單主檔 id
	@GetMapping("/{oid}/item")
	public String indexItem(Model model, @PathVariable("oid") Long oid) {
		Order order = orderRepository.findById(oid).get();
		OrderItem orderItem = new OrderItem();
		List<Product> products = productRepository.findAll();
		model.addAttribute("order", order);
		model.addAttribute("orderItem", orderItem);
		model.addAttribute("products", products);
		return "order-item";
	}
	
	@PostMapping("/{oid}/item")
	// 新增訂單項目
	public String createItem(OrderItem orderItem, BindingResult result, Model model, @PathVariable("oid") Long oid) {
		// 驗證資料
		inventoryValidator.validate(orderItem, result);
		if(result.hasErrors()) {
			Order order = orderRepository.findById(oid).get();
			List<Product> products = productRepository.findAll();
			model.addAttribute("order", order);
			model.addAttribute("orderItem", orderItem);
			model.addAttribute("products", products);
			return "order-item";
		}
		// 訂單檔(主檔)
		Order order = orderRepository.findById(oid).get();
		// 訂單項目與訂單檔(主檔)建立關聯 (ps:由多的一方建立關聯)
		orderItem.setOrder(order);
		orderItemRepository.save(orderItem);
		return "redirect:/order/" + oid + "/item";
	}
	
	@GetMapping("/edit/{oid}/item/{iid}") // 項目修改頁面的呈現
	public String editItem(@PathVariable("oid") Long oid, @PathVariable("iid") Long iid, Model model) {
		Order order = orderRepository.findById(oid).get();
		OrderItem orderItem = orderItemRepository.findById(iid).get();
		List<Product> products = productRepository.findAll();
		model.addAttribute("order", order);
		model.addAttribute("orderItem", orderItem);
		model.addAttribute("products", products);
		return "order-item";
	}
	
	@PutMapping("/{oid}/item")
	// 修改訂單項目
	public String updateItem(OrderItem orderItem, @PathVariable("oid") Long oid) {
		// 訂單檔(主檔)
		Order order = orderRepository.findById(oid).get();
		// 訂單項目與訂單檔(主檔)建立關聯 (ps:由多的一方建立關聯)
		orderItem.setOrder(order);
		orderItemRepository.save(orderItem);
		return "redirect:/order/" + oid + "/item";
	}
	
	@GetMapping("/delete/{oid}/item/{iid}")
	// 刪除採購明細檔
	public String deleteItem(@PathVariable("oid") Long oid, @PathVariable("iid") Long iid) {
		orderItemRepository.deleteById(iid);
		return "redirect:/order/" + oid + "/item";
	} 
	
}
