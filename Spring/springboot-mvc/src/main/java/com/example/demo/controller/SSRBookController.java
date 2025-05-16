package com.example.demo.controller;

import java.util.List;

import org.modelmapper.internal.bytebuddy.utility.privilege.GetMethodAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/ssr/book")
public class SSRBookController {
	@Autowired
	private BookService bookService;
	
	// 查詢所有書籍
	@GetMapping // 跑 "/ssr/book"
	public String findAllBooks(Model model) {
		// 要在 Spring framework 將資料傳給 JSP 方式是由 Model 容器儲存 Attribute，
		// 類似之前的 request.setAttributes() 的方式，request 由 Model 取代。
		
		List<Book> books = bookService.findAllBooks();
		model.addAttribute("books", books);
		return "book-list"; 
		// 對應到 "/WEB-INF/view/book-list.jsp"
		// 簡化 req.getRequestDispatcher("jsp").forward(req, resp); 
	}
	
	
	// 新增書籍
	@PostMapping("/add")
	public String addBook(Book book, Model model) {
		try {
			bookService.addBook(book);
		} catch (BookException e) {
			model.addAttribute("message", "新增時發生錯誤" + e.getMessage());
			return "error";
		}
		return "redirect:/ssr/book"; 
		/* 重新執行 ssr/book
		 * 事實上為 http 指令，Servlet 將網址丟給瀏覽器，
		 * 要求瀏覽器執行這個網頁路徑(JSP)，並不是 Service 端重新執行。
		 * 可以寫成：
		 * return "redirect:http://localhost:8080/ssr/book";
		 * 意思就是 addBook 回傳 redirect ssr/book，
		 * 要求瀏覽器重新執行 public String findAllBooks(Model model)，顯示所有書籍。
		 */
	}
	
	// 刪除書籍
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable Integer id, Model model) {
		try {
			bookService.deleteBook(id);
		} catch (BookException e) {
			model.addAttribute("message", "刪除時發生錯誤" + e.getMessage());
			return "error";
		}
		return "redirect:/ssr/book"; 
	}
	
	// 取得修改頁面：
	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable Integer id, Model model) {
		try {
			Book book = bookService.getBookById(id);
			// 將 book 傳出去，傳到 book-edit 
			model.addAttribute("book", book); 
			return "book-edit";
			
		} catch (Exception e) {
			model.addAttribute("message", "查無資料。" + e.getMessage());
			return "error";
		}
	}
	
	
	// 更新書籍：
	@PutMapping("/edit/{id}")
	public String editBook(@PathVariable Integer id, Book book, Model model) {
		try {
			bookService.updateBook(id, book);
			
		} catch (BookException e) {
			model.addAttribute("message", "更新時發生錯誤" + e.getMessage());
			return "error";
		}
		return "redirect:/ssr/book"; 
	}
}
