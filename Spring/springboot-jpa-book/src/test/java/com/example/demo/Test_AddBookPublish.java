package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.BookPublish;
import com.example.demo.model.entity.Publisher;
import com.example.demo.repository.BookPublishRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;

@SpringBootTest
public class Test_AddBookPublish {
	
	@Autowired
	private PublisherRepository publisherRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookPublishRepository bookPublishRepository;
	
	@Test
	public void addPublish() {
		
		// 目標書籍
		Book book = bookRepository.findById(3).get();
		
		// 目標出版商
		Publisher publisher = publisherRepository.findById(1).get();
		
		// 建立 publish 
		BookPublish publish = new BookPublish();
		
		publish.setBook(book);
		publish.setPublisher(publisher);
		
		bookPublishRepository.save(publish);
		
	}
}
