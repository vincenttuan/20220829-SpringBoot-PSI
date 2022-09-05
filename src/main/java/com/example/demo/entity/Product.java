package com.example.demo.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name; // 商品名稱
	
	@Column
	private Integer cost; // 成本
	
	@Column
	private Integer price; // 定價
	
	@OneToMany(mappedBy = "product")
	@OrderBy("id ASC")
	private Set<PurchaseItem> purchaseItems = new LinkedHashSet<>();
	
}
