package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAllBooks();
	}

	@Override
	public Book getBookbyId(Integer id) throws BookException {
		Optional<Book> optBook = bookRepository.getBookbyId(id);
		if(optBook.isEmpty()) {
			throw new BookException("id" + id + "查無資料");
		}
		return optBook.get();
	}

	@Override
	public void addBook(Book book) throws BookException {
		if(!bookRepository.addBook(book)) {
			throw new BookException("新增失敗" + book);
		}
	}

	@Override
	public void updateBook(Integer id, Book book) throws BookException {
		if(!bookRepository.updateBook(id, book)) {
			throw new BookException("更新失敗" + book);
		}
	}

	@Override
	public void updateBookName(Integer id, String name) throws BookException {
		Book book = getBookbyId(id);
		book.setName(name);
		updateBook(book.getId(), book);
	}

	@Override
	public void updateBookPrice(Integer id, Double price) throws BookException {
		Book book = getBookbyId(id);
		book.setPrice(price);
		updateBook(book.getId(), book);
		
	}

	@Override
	public void updateBookNamePrice(Integer id, String name, Double price) throws BookException {
		Book book = getBookbyId(id);
		book.setName(name);
		book.setPrice(price);
		updateBook(book.getId(), book);
		
	}

	@Override
	public void deleteBook(Integer id) throws BookException {
		if(!bookRepository.deleteBook(id)) {
			throw new BookException("刪除失敗" + id);
		}
	}
}
