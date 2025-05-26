package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@SpringBootTest
public class Test_AddBooks {
	
	@Autowired 
	private BookRepository bookRepository;
	
	// 要尋找作者，所以要呼叫 Author Repository 物件
	@Autowired
	private AuthorRepository authorRepository;
	
	@Test
	public void addBook(){
		// 尋找作者：
		Optional<Author> optauthor = authorRepository.findById(1);
		if(optauthor.isEmpty()) {
			System.err.println("查無作者");
		}
		Author author = optauthor.get();
		
		// 建立書籍並關聯作者：
		Book book = new Book();
		book.setName("Java入門");
		book.setAuthor(author); // 建立關聯作者。
		
		// 第二本書
		Book book2 = new Book();
		book2.setName("Spring 實戰");
		book2.setAuthor(author); // 建立關聯作者。
		// 因為 author 可以有多本書，所以可以用同一個作者。
		
		// 儲存
		bookRepository.save(book);
		bookRepository.save(book2);
	}

}
