package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.exception.CertException;
import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.CertService;
import com.example.demo.service.UserService;

@SpringBootTest
public class CertServiceTest {
	
	@Autowired
	private CertService certService;
	
	@Test 
	public void test() throws CertException {
		System.out.println(certService.getCert("john", "1234"));
	}
}
