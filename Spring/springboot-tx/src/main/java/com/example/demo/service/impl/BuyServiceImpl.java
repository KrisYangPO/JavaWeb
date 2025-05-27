package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InsufficientAmountException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.BuyService;

import jakarta.transaction.Transactional;

@Service
public class BuyServiceImpl implements BuyService{
	
	@Autowired
	private BookService bookService;
	
	@Override
	@Transactional(
			rollbackOn = {InsufficientAmountException.class},
			dontRollbackOn = {RuntimeException.class}
	)
	public void buyOneBook(String username, Integer bookId) throws InsufficientAmountException {
		System.out.printf("%s 要買書%n", username);
		
		// 1. 查詢書本價格
		Integer bookPrice = bookService.getBookPrice(bookId);
		System.out.printf("bookId:%d, bookPrice:%d%n", bookId, bookPrice);
		
		// 2. 減少庫存
		bookService.reduceBookAmount(bookId, 1); //減去一本
		
		// 3. 修改餘額
		bookService.reduceWalletBalance(username, bookPrice);
		
		// 4 .處理完成。
		System.out.printf("%s 結帳完成%n", username);
	}

}

/* 這裡會有一個問題：
 * 當使用者餘額不足的時候，就會產生錯誤，第三步驟修改餘額就會停止，
 * 資料庫中，Wallet 就會顯示餘額(不足買書)，
 * 但是會發現 Stock 庫存已經被扣掉，發生已經購買的動作，
 * 這是因為餘額不足的判斷之前就已經扣掉庫存了，扣掉之後才發現沒餘額可以買了，
 * 但程式不會自己知道這是交易行為不能扣掉，因為執行就執行，資料庫就會改變。
 * 
 * 因此加入 @Transactional 標籤，他就會確保這個方法裡，
 * 所有程式都要執行成功，如果其中的程式發生 RuntimeException，
 * 預設只有發生 RuntimeException，才會將前面執行的程式結果會進行回滾，
 * (也可以設計回滾抓取的 Exception 型態)
 * 復原執行前的所有紀錄，這就可以確保方法裡程式成功執行，才會更改所有變更。
 * 
 * 此外，可以透過 rollbackOn={特定或是自訂 EXception.class} 控制一定要回滾，
 * 跟 dontRollbackOn={特定或是自訂 EXception.class} 控制 transactional 要避開哪些 Exception 回滾。
 * 
 */
