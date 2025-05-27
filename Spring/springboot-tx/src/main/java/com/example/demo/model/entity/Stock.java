package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
	
	@Id
	private Integer bookId; // 與書本 id 對應。
	
	private Integer bookAmount;
	

}
