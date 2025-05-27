package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Biography;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BiographRepository;

@SpringBootTest
public class Test_ReadBio {
	
	@Autowired
	private BiographRepository biographRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Test
	public void read() {
//		List<Biography> biographies = biographRepository.findAll();
		List<Biography> biographies = biographRepository.findAllwithAuthor();
		biographies.forEach(bio -> {
			System.out.printf("傳記編號：%d 內容：%s 作者：%s%n",
					bio.getId(), bio.getDetails(), bio.getAuthor().getName());
		});
	}
	
}
