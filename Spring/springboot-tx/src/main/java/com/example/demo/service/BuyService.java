package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.exception.InsufficientAmountException;

@Service
public interface BuyService {
	
	void buyOneBook(String username, Integer bookId) throws InsufficientAmountException;
}
