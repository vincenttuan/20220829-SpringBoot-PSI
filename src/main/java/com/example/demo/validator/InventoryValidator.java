package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entity.OrderItem;
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
			
		}
		
		
	}
	
	
}
