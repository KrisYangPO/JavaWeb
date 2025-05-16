package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	// Service 呼叫這個介面繼承的 DAO 實作時，
	// 會發現有多個 bookRepository 實作類別：BookRepositoryJdbcImpl 和 BookRepositoryImpl
	// 可以用 @Primary 加在你要使用的實作類別，但是不好用。
	// 或是在 Service 當中使用 @Qualifier("bookRepositoryJdbcImpl") -> 類別第一個字母改小寫。
	// 這樣就可以指定 Service 要用哪個 DAO 介面實作物件：
	@Qualifier("bookRepositoryJdbcImpl")
	private BookRepository bookRepository;
	
	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAllBooks();
	}

	@Override
	public Book getBookById(Integer id) throws BookException {
		Optional<Book> optBook = bookRepository.getBookById(id);
		if(optBook.isEmpty()) {
			throw new BookException("id: " + id + ", 查無此書");
		}
		return optBook.get();
	}

	@Override
	public void addBook(Book book) throws BookException {
		if(!bookRepository.addBook(book)) {
			throw new BookException("新增失敗, " + book);
		}
		
	}

	@Override
	public void updateBook(Integer id, Book book) throws BookException {
		if(!bookRepository.updateBook(id, book)) {
			throw new BookException("修改失敗, id: " + id + ", " + book);
		}
	}

	@Override
	public void updateBookName(Integer id, String name) throws BookException {
		Book book = getBookById(id);
		book.setName(name);
		updateBook(book.getId(), book);
	}

	@Override
	public void updateBookPrice(Integer id, Double price) throws BookException {
		Book book = getBookById(id);
		book.setPrice(price);
		updateBook(book.getId(), book);
	}

	@Override
	public void updateBookNameAndPrice(Integer id, String name, Double price) throws BookException {
		Book book = getBookById(id);
		book.setName(name);
		book.setPrice(price);
		updateBook(book.getId(), book);
	}

	@Override
	public void deleteBook(Integer id) throws BookException {
		if(!bookRepository.deleteBook(id)) {
			throw new BookException("刪除失敗, id: " + id);
		}
		
	}
	
}