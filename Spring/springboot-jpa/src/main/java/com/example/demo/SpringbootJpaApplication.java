package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// @ServletComponentScan 會啟用 WebFilter 掃描，
// 因為 SpringBoot 預設會忽略 JavaWeb 的基本 Annotation: @WebFilter

@SpringBootApplication
@ServletComponentScan 
public class SpringbootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}
}
