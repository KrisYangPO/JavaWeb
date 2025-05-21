package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.UserService;

@SpringBootTest
public class UserJPARead {
	
	@Autowired
	private UserService userService;
	
	@Test 
	public void test() {
		System.out.println(userService.getUser("john"));
		System.out.println(userService.getUser("mary"));
	}
}
