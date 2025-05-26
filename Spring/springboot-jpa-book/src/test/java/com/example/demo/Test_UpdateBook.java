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
public class Test_UpdateBook {
	
	// 變更書籍作者
	
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void updateBookName() {
		
		// 先查有沒有這個作者編號 2 的資訊
		Optional<Author> optAuthor = authorRepository.findById(2);
		if(optAuthor.isEmpty()) {
			System.err.println("查無作者");
			return;
		}
		
		// 在查有沒有書籍編號是 1 的書籍：
		Optional<Book> optBook = bookRepository.findById(1);
		if(optBook.isEmpty()) {
			System.err.println("查無書籍");
			return;
		}
		
		// 取得編號 2 的作者與編號 1 的書籍
		Author author2 = optAuthor.get();
		Book book1 = optBook.get();
		
		// 編號1的書的作者更新成編號為 2 的作者
		book1.setAuthor(author2);
		
		// 更新：
		bookRepository.save(book1);
		
	}
}
