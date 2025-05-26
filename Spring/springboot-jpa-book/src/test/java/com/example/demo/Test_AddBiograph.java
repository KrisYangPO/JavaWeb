package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Biography;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BiographRepository;

@SpringBootTest
public class Test_AddBiograph {
	
	@Autowired
	private BiographRepository biographRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Test
	public void add() {
		// 先查詢作者
		Optional<Author> optAuthor = authorRepository.findById(2);
		if(optAuthor.isEmpty()) {
			return;
		}
		Author author = optAuthor.get();
		
		// 建立 bio
		Biography biography = new Biography();
		biography.setDetails("BadmintonMatch 作者...");
		biography.setAuthor(author);
		
		biographRepository.save(biography);
	}
}
