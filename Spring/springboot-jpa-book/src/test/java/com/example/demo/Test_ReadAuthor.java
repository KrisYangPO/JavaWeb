package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BiographRepository;
import com.example.demo.repository.BookRepository;

@SpringBootTest
public class Test_ReadAuthor {
	
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BiographRepository biographRepository;
	
	@Test
	public void read() {
		// 查所有作者
		List<Author> authors = authorRepository.findAll();
		
		authors.forEach(author -> {
			System.out.printf("序號:%d 姓名:%s 自傳：%s%n",
					author.getId(), author.getName(), author.getBiography().getDetails());
		});
		
		List<Author> authors2 = authorRepository.findAllWithBooks();
		authors2.forEach(author -> {
			System.out.printf("序號:%d 姓名:%s%n 著作量:%d%n",
					author.getId(), author.getName(), author.getBooks().size());
		});	
		
		// 查詢作者與傳記
		
	}

}
