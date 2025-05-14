package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Book;

public interface BookRepository {
	List<Book> findAllBooks();
	Optional<Book> getBookbyId(Integer id);
	Boolean addBook(Book book);
	Boolean updateBook(Integer id, Book book);
	Boolean deleteBook(Integer id);
}
