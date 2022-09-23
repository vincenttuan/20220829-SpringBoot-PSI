package com.example.demo.entity.view;

// 即時庫存
public interface Inventory {
	Long getId(); // 序號
	String getName(); // 商品名稱
	Integer getCost(); // 商品成本
	Integer getPrice(); // 商品售價
	Integer getAmount1(); // 採購數量
	Integer getAmount2(); // 銷售數量
}
