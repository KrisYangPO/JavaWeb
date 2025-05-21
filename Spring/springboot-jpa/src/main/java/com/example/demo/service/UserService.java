package com.example.demo.service;

import com.example.demo.model.dto.UserDto;

public interface UserService {
	public UserDto getUser(String name);
	public void addUser(String name, String password, String email, Boolean active, String role);
	// 往下自訂。
	
}
