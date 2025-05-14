package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

// Repository 就是 Spring-mvc 當中的 DAO 層面。

@Repository
public class BookRepositoryImpl implements BookRepository {
	private static List<Book> books = new CopyOnWriteArrayList<>();
	
	// 資料庫初始資料有四本書。
	static {
		books.add(new Book(1, "小叮噹", 12.5, 20, false));
		books.add(new Book(2, "老夫子", 10.5, 20, false));
		books.add(new Book(3, "好小子", 8.5, 40, true));
		books.add(new Book(4, "鏈鋸人", 14.5, 50, true));
	}
	
	// 顯示所有書
	public List<Book> findAllBooks(){
		return books;
	}
	// 根據編號找書
	public Optional<Book> getBookbyId(Integer id) {
		return books.stream().filter(b->b.getId().equals(id)).findFirst();
	}
	// 新增書籍
	public Boolean addBook(Book book) {
		return books.add(book);
	}
	// 更新書籍：
	public Boolean updateBook(Integer id, Book book) {
		Book uptbook = books.set(id, book);
		return uptbook != null; 
	}
	// 刪除書：
	public Boolean deleteBook(Integer id) {
		Optional<Book> optBook = getBookbyId(id);
		if(optBook.isPresent()) {
			books.remove(id);
		}
		return false;
	}
}
