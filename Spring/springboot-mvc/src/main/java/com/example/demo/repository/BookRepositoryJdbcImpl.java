package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public class BookRepositoryJdbcImpl implements BookRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Book> findAllBooks() {
		// 不可以用 select *，是潛規則。
		String sql = "select id, name, price, amount, pub from book";
		
		// 將 sql 查詢結果，自動注入到 Book 物件的對應屬性中。
		// 前提是 SQL 欄位名稱要與 Book 物件中的屬性名稱一致 (Java 反射機制)。
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
	}

	
	@Override
	public Optional<Book> getBookById(Integer id) {
		String sql = "select id, name, price, amount, pub from book where id =?";
		
		// 無論查到多筆還是一筆資料，他都會回傳 List<>
		// 有變數存在就在最後面加上參數，將 sql 變數 ? 帶入
		List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), id);
		
		// 如果 books 是空集合，回傳 Optional.empty，反之取出第一筆資料。
		// return books.isEmpty()? Optional.empty() : Optional.of(books.get(0));
		// 用 try-catch 做比較好：
		try {
			// queryForObject 意思是只查詢單筆。
			Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
			return Optional.of(book); // 將 Book 物件轉成 Optional 物件。
			
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public boolean addBook(Book book) {
		String sql = "insert into book(name, price, amount, pub) values(?,?,?,?)";
		
		// 檢查 pub 是否為 null，如果 checkbox 沒有勾選就會是 null，
		// 因此在資料庫端判斷如果是 null 就改成 false。
		if(book.getPub() == null) {
			book.setPub(false);
		}
		// 執行新增
		int rows = jdbcTemplate.update(sql, book.getName(), book.getPrice(), book.getAmount(), book.getPub());
		
		// 如果有更新就會大於零，沒有就沒有更新成功。
		return rows > 0; 
	}

	@Override
	public boolean updateBook(Integer id, Book book) {
		String sql = "update book set name = ?, price = ?, amount = ?, pub = ? where id = ?";
		// 最後給予 Sql 變數 "?" 的參數為 id;
		
		// 檢查 pub 是否為 null，如果 checkbox 沒有勾選就會是 null，
		// 因此在資料庫端判斷如果是 null 就改成 false。
		if(book.getPub() == null) {
			book.setPub(false);
		}
		// 執行修改。
		int rows = jdbcTemplate.update(sql, book.getName(), book.getPrice(), book.getAmount(), book.getPub(), id);
		return rows > 0;
	}

	@Override
	public boolean deleteBook(Integer id) {
		String sql = "delete from book where id=?";
		int rows = jdbcTemplate.update(sql, id);
		return rows >0; 
	}
	
}
