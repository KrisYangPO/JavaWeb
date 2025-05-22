package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BMI;
import com.example.demo.response.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/practice")
public class PracticeAPI {
	
	
//	// 計算 BMI
//	// 目標網址：http://localhost:8080/practice/bmi?h=170.5&w=60.5
//	@GetMapping("/bmi")
//	public ApiResponse<BMI> calBMI(@RequestParam Double h, Double w) {
//		// 網頁參數錯誤：
//		if(h == null || w == null) {
//			return ApiResponse.error(400, "計算錯誤");
//		}
//		// 計算 bmi
//		double bmi = w/ Math.pow(h/100, 2);
//		return ApiResponse.success("成功執行", new BMI(h, w, bmi));
//	}
	
	// 計算 BMI 透過 ResponseEntity 傳送
	// 目標網址：http://localhost:8080/practice/bmi?h=170.5&w=60.0
	@GetMapping("/bmiRes")
	public ResponseEntity<ApiResponse<BMI>> calBMI(
			@RequestParam(required = false) Double h,
			@RequestParam(required = false) Double w) {
		
		// 如果網址參數錯誤：
		if(h == null || w == null) {
			return ResponseEntity.badRequest().body(ApiResponse.error("請提供身高(h)與體重(w)數值。"));
		}
		
		// 計算 bmi
		double bmi = w/ Math.pow(h/100, 2);
		return ResponseEntity.ok(ApiResponse.success("成功執行", new BMI(h, w, bmi)));
	}
	
}
