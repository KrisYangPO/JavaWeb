package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 根據數據新增自動累加 id 數值
	private Integer id;
	
	// nullable = false: 不能為 null
	@Column(length = 50, nullable = false)
	private String name;
	
	// 設定自傳
	@OneToOne(mappedBy = "author")
	private Biography biography;
	
	// 建立關聯
	@OneToMany(mappedBy = "author")
	private List<Book> books;

	// 再多對一的關係中，多方在建立關聯時是主控端，
	// 而單方為被控端。
}
