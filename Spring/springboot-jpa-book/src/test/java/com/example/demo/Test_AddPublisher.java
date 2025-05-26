package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Publisher;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;

@SpringBootTest
public class Test_AddPublisher {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository publsheRepository;
	
	@Test
	public void add() {
		Book book1 = bookRepository.findById(1).get();
		Book book4 = bookRepository.findById(4).get();
		
		Publisher publisher1 = new Publisher();
		publisher1.setName("碁峰資訊");
		publisher1.addBook(book1);
		publisher1.addBook(book4);
		
		Publisher publisher2 = new Publisher();
		publisher2.setName("第三波資訊");
		publisher2.addBook(book1);
		
		Publisher publisher3 = new Publisher();
		publisher3.setName("天下文化");
//		publisher3.addBook(book4);
		
		publsheRepository.save(publisher1);
		publsheRepository.save(publisher2);
		publsheRepository.save(publisher3);
	}
}
