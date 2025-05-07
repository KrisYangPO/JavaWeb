package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
// 透過標註 @Controller 告訴 SpringBoot 此檔案是 Controller

@Controller
public class HelloController {
	
	// 類似 @WebServlet("/hello") 的功能，在網頁執行：http://localhost:8080/hello
	@GetMapping("/hello")
	// 回應瀏覽器的內容
	@ResponseBody		  
	public String hello() {
		return "hello" + new Date();
	}
	
	@GetMapping("/hi")
	@ResponseBody
	public String hi() {
		return "Hi." + new Date();
	}
	
	// 寫了 JSP 就不用給 ResponseBody，他就會去找 JSP 檔
	@GetMapping("/welcome")
	// Model 裡面放的就是要傳給 JSP 的資料 (相對於 Servlet 中的 req.setAttribute("var", var))
	public String welcome(Model model) {
		model.addAttribute("name", "PO");
		model.addAttribute("now", new Date());
		// return 的東西就是取 welcome.jsp 檔名的部分 (類似 requestDispatcher 指定到哪個 JSP 檔案)
		return "welcome"; 
	}
	
}
