package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BMI;
import com.example.demo.response.ApiResponse;

// RestController 可以免去撰寫 @ResponseBody，
// 但若要透過 jsp 渲染則不適用。

@RestController() 
@RequestMapping("/api")
// @RequestMapping 統一這個 ApiController 下的所有 URL 都會有前綴：/api
// 所以後面的路徑其實是：/api/home/ 和 /api/
public class ApiController {
	
	/* 1. 首頁
	 * 路徑：/api/home
	 * 路徑：/api/
	 * 網址：http://localhost:8080/api/home
	 * 網址：http://localhost:8080/api
	 */
	// 同時選擇多個
	@GetMapping(value= {"/home", "/"})
	public String home() {
		return "我是首頁";
	}
	
	/* 2. 網址帶參數 
	 * 路徑：/greet?name=Mary
	 * 路徑：/greet?name=John&age=18
	 * 網址：http://localhost:8080/api/greet?name=John&age=18
	 * 結果：Hi John, 18 (成年)
	 * 網址：http://localhost:8080/api/greet?name=Mary
	 * 結果：Hi Mary, 0 (未成年)
	 * 限制：name 參數一定要加，age 為可選參數 (default:0)
	 */
	@GetMapping("/greet")
	// 用 RequestParam 找到網址中的 name 參數，為必填的數值，並將參數值存在 username 變數
	// 用 RequestParam 找到網址中的 age 參數，非必要參數，並將參數值儲存在 userage 變數中。
	public String greet(@RequestParam(value = "name", required=true) String username,
			@RequestParam(value = "age", required=false, defaultValue = "0") Integer userage) {
		
		String result = String.format("Hi %s %d (%s)",
				username, userage, userage > 18? "成年":"未成年");
		
		return result;
	}
	
	// 精簡寫法
	// 方法參數(greet2 設定的參數)，與請求參數名 (網址上的參數名稱) 稱相同
	@GetMapping("/greet2")
	public String greet2(
			@RequestParam String name,
			@RequestParam(defaultValue = "0") Integer age) {
		
		String result = String.format("Hi %s %d (%s)",
				name, age, age > 18? "成年":"未成年");
		
		return result;
	}
	
	
	/*
	 * 4. Lab 練習 I
	 * 路徑: /bmi?h=170&w=60
	 * 網址: http://localhost:8080/api/bmi?h=174.5&w=70
	 * 執行結果: bmi = 20.76
	 * 建立類似 JSON 格式：
	 * {
	 *  "status": 200,
	 *  "message": "BMI 計算成功",
	 *  "data":{
	 *  	"height": 170.0,
	 *  	"weight": 60.0,
	 *  	"bmi": 20.76
	 *  }
	 * }
	 * 
	 * */
	
	// 設定 produces = "application/json;charset = utf-8" 就會將物件轉成 JSON 格式。
	@GetMapping(value = "/bmi", produces = "application/json;charset = utf-8")
	public ApiResponse calBMI(
			@RequestParam(value = "h", required = false) Double hei, 
			@RequestParam(value = "w", required = false) Double wei
			) {
		
		if(hei == null || wei == null) {
			return ApiResponse.error(400, "Please provide weight and height.");
		}
		
		// 計算 bmi
		Double bmi = wei/ ((hei/100.0)*(hei/100.0));
		return ApiResponse.success("BMI testing success.", new BMI(hei, wei, bmi));
	}
}
