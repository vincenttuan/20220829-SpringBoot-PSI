package com.example.demo.testing.create;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
public class CreatePurchase {
	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void test() {
		// 資料預備
		// Purchase 部門的員工 Bob 對 富貴芬芳 採購 百合花 100 朵 與 鬱金香 200 朵
		Supplier s1 = supplierRepository.findById(1L).get(); // 富貴芬芳
		Employee e4 = employeeRepository.findById(4L).get(); // Bob
		Product p1 = productRepository.findById(1L).get(); // 百合花
		Product p2 = productRepository.findById(2L).get(); // 鬱金香
		
		// 建立採購單
		Purchase pu = new Purchase();
		pu.setDate(new Date());  // 設定採購日期
		// 配置關聯
		pu.setSupplier(s1); // 配置供應商: 富貴芬芳
		pu.setEmployee(e4); // 配置員工: Bob
		
		// 建立採購明細 1
		PurchaseItem pi1 = new PurchaseItem();
		pi1.setAmount(100); // 數量 100 朵
		// 配置關聯
		pi1.setPurchase(pu); // 配置採購單(此採購明細是隸屬於哪一個採購單)
		pi1.setProduct(p1); // 配置商品(此採購明細要購買的商品)
		
		// 建立採購明細 2
		PurchaseItem pi2 = new PurchaseItem();
		pi2.setAmount(200); // 數量 200 朵
		// 配置關聯
		pi2.setPurchase(pu); // 配置採購單(此採購明細是隸屬於哪一個採購單)
		pi2.setProduct(p2); // 配置商品(此採購明細要購買的商品)
		
		// 保存
		purchaseRepository.save(pu); // 採購單(主檔)
		purchaseItemRepository.save(pi1); // 採購單明細(明細檔)
		purchaseItemRepository.save(pi2); // 採購單明細(明細檔)
	} 
}










