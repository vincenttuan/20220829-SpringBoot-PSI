package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Product;
import com.example.demo.entity.Purchase;
import com.example.demo.entity.PurchaseItem;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchaseItemRepository;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.SupplierRepository;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		Purchase purchase = new Purchase();
		List<Purchase> purchases = purchaseRepository.findAll();
		List<Supplier> suppliers = supplierRepository.findAll();
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("purchase", purchase);
		model.addAttribute("purchases", purchases);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("employees", employees);
		return "purchase";
	}
	
	@PostMapping("/")
	public String create(Purchase purchase) {
		purchaseRepository.save(purchase);
		return "redirect:/purchase/";
	}
	
	@GetMapping("/edit/{id}") // ?????????????????????
	public String edit(@PathVariable("id") Long id, Model model) {
		Purchase purchase = purchaseRepository.findById(id).get();
		List<Supplier> suppliers = supplierRepository.findAll();
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("purchase", purchase);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("employees", employees);
		return "purchase-edit";
	}
	
	@PutMapping("/{id}") // ????????????????????????
	public String update(@PathVariable("id") Long id, Purchase purchase) {
		purchase.setId(id);
		purchaseRepository.save(purchase);
		return "redirect:/purchase/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		purchaseRepository.deleteById(id);
		return "redirect:/purchase/";
	}
	
	// ?????????????????????
	// pid -> ??????????????? id
	@GetMapping("/{pid}/item")
	public String indexItem(Model model, @PathVariable("pid") Long pid) {
		Purchase purchase = purchaseRepository.findById(pid).get();
		PurchaseItem purchaseItem = new PurchaseItem();
		List<Product> products = productRepository.findAll();
		model.addAttribute("purchase", purchase);
		model.addAttribute("purchaseItem", purchaseItem);
		model.addAttribute("products", products);
		return "purchase-item";
	}
	
	@PostMapping("/{pid}/item")
	// ??????????????????
	public String createItem(PurchaseItem purchaseItem, @PathVariable("pid") Long pid) {
		// ?????????(??????)
		Purchase purchase = purchaseRepository.findById(pid).get();
		// ????????????????????????(??????)???????????? (ps:???????????????????????????)
		purchaseItem.setPurchase(purchase);
		purchaseItemRepository.save(purchaseItem);
		return "redirect:/purchase/" + pid + "/item";
	}
	
	@GetMapping("/edit/{pid}/item/{iid}") // ???????????????????????????
	public String editItem(@PathVariable("pid") Long pid, @PathVariable("iid") Long iid, Model model) {
		Purchase purchase = purchaseRepository.findById(pid).get();
		PurchaseItem purchaseItem = purchaseItemRepository.findById(iid).get();
		List<Product> products = productRepository.findAll();
		model.addAttribute("purchase", purchase);
		model.addAttribute("purchaseItem", purchaseItem);
		model.addAttribute("products", products);
		return "purchase-item";
	}
	
	@PutMapping("/{pid}/item")
	// ??????????????????
	public String updateItem(PurchaseItem purchaseItem, @PathVariable("pid") Long pid) {
		// ?????????(??????)
		Purchase purchase = purchaseRepository.findById(pid).get();
		// ????????????????????????(??????)???????????? (ps:???????????????????????????)
		purchaseItem.setPurchase(purchase);
		purchaseItemRepository.save(purchaseItem);
		return "redirect:/purchase/" + pid + "/item";
	}
	
	@GetMapping("/delete/{pid}/item/{iid}")
	// ?????????????????????
	public String deleteItem(@PathVariable("pid") Long pid, @PathVariable("iid") Long iid) {
		purchaseItemRepository.deleteById(iid);
		return "redirect:/purchase/" + pid + "/item";
	} 
	
}
