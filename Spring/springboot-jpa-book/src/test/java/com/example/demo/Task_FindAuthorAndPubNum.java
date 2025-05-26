package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@SpringBootTest
public class Task_FindAuthorAndPubNum {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void getAutherAndPubNum() {
		List<Author> authors = authorRepository.findAllWithBooks();
		authors.forEach(author -> {
			System.out.printf("序號:%d 姓名:%s%n 著作量:%d%n",
					author.getId(), author.getName(), author.getBooks().size());
		});
	}
}
