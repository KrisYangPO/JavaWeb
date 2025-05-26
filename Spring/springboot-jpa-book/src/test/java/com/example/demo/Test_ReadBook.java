package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@SpringBootTest
public class Test_ReadBook {
	
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void read() {
		List<Book> books = bookRepository.findAll();
		
		// 查詢書
		books.forEach(book ->{
			System.out.printf("序號：%d 書名:%s%n", book.getId(), book.getName());
		});
		
		// 查詢書 + 作者
		books.forEach(book ->{
			System.out.printf("序號：%d 書名:%s 作者：%s%n", book.getId(), book.getName(), book.getAuthor().getName());
		});
		
	}
}
