package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// nullable = false: 不能為 null
	@Column(length = 100, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
	// 在多對一的關係中，多方在建立關聯時是主控端，
	// 而單方為被控端。
	
//	@ManyToMany(mappedBy = "books")
//	private List<Publisher> publishers;
	@OneToMany(mappedBy="book")
	private List<BookPublish> bookPublishs;
}
