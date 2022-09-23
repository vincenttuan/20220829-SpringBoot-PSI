package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.view.Inventory;
import com.example.demo.repository.ProductRepository;

@Component
public class InventoryValidator implements Validator {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return OrderItem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderItem orderItem = (OrderItem)target;
		
		if(orderItem.getAmount() == null || orderItem.getAmount() == 0) {
			errors.rejectValue("amount", "order_item.amount.required", "請輸入數量");
		} else {
			// 此商品的庫存數量是否足夠下單 ?
			Long id = orderItem.getProduct().getId();
			Inventory inventory = productRepository.findInventoryById(id);
			// amount1 : 進貨數量
			// amount2 : 銷售數量
			// remaining : 庫存剩餘數量 (進貨數量 - 銷售數量)
			int amount1 = inventory.getAmount1() == null ? 0 : inventory.getAmount1();
			int amount2 = inventory.getAmount2() == null ? 0 : inventory.getAmount2();
			int remaining = amount1 - amount2;
			// 購買數量是否大於庫存剩餘數量
			if(orderItem.getAmount() > remaining) {
				errors.rejectValue("amount", "order_item.amount.insufficient", "目前庫存數量不足 (庫存: " + remaining + ")");
			}
			
		}
		
		
	}
	
	
}
