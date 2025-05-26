package com.example.demo.model.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Publisher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	
	// @JoinTable：指定中介表名稱與外鍵欄位名稱。
	@ManyToMany
	@JoinTable(
			// 建立一個名為 “publisher_book” 的關聯資料表。
			name="publisher_book",
			// 表示**主控端（owning side）**這邊的欄位名稱（外鍵）
			// 因為這段程式碼通常會放在 Publisher 實體裡，所以這個外鍵會指向 Publisher 的主鍵。
			// publisher_book.publisher_id 是指向 Publisher.id 的外鍵
			joinColumns = @JoinColumn(name = "publisher_id"),
			// publisher_book.book_id 是指向 Book.id 的外鍵
			inverseJoinColumns = @JoinColumn(name = "book_id"))
	
	private List<Book> books;
	
	
	// 在 Entity 中自建新增書籍
	public void addBook(Book book) {
		
		if (books == null) {
			books = new CopyOnWriteArrayList<>();
		}
		books.add(book);
	}	
}
