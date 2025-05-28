package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.BookPublish;
import com.example.demo.repository.BookPublishRepository;

@SpringBootTest
public class Test_ReadBookPublish {
	
	@Autowired
	private BookPublishRepository bookPublishRepository;
	
	
	@Test
	public void readAllBookPublish() {
		
		List<BookPublish> bookPublishs = bookPublishRepository.findAllWithBookAndPublisher();
		
		bookPublishs.forEach(bp -> {
			System.out.printf("出版社:%s\t出版書籍:%s\t出版編號:%s%n", 
					bp.getPublisher().getName(), bp.getBook().getName(), bp.getId()
					);
		});
		
	}

}
