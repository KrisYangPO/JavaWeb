package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
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
	
//	// 設定 produces = "application/json;charset = utf-8" 就會將物件轉成 JSON 格式。
//	@GetMapping(value = "/bmi", produces = "application/json;charset = utf-8")
//	public ApiResponse calBMI(
//			@RequestParam(value = "h", required = false) Double hei, 
//			@RequestParam(value = "w", required = false) Double wei
//			) {
//		
//		if(hei == null || wei == null) {
//			return ApiResponse.error(400, "Please provide weight and height.");
//		}
//		
//		// 計算 bmi
//		Double bmi = wei/ ((hei/100.0)*(hei/100.0));
//		return ApiResponse.success("BMI testing success.", new BMI(hei, wei, bmi));
//	}
	
	
	// 不親自設定狀態碼：
	// 將 ApiResponse 有關狀態碼和 status 的屬性與參數移除，
	@GetMapping(value = "/bmi", produces = "application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<BMI>> calcBmi(
			@RequestParam(required = false) Double h, 
			@RequestParam(required = false) Double w) {
		
		if(h == null || w == null) {
			// badRequest().body() 回應發生錯誤時，要在 body() 執行什麼資訊
			return ResponseEntity.badRequest().body(ApiResponse.error("請提供身高(h)或體重(w)"));
		}
		
		double bmi = w / Math.pow(h/100, 2);
		// 當程式沒問題，ok()當中執行什麼
		return ResponseEntity.ok(ApiResponse.success("BMI 計算成功", new BMI(h, w, bmi)));
	}
	
	
	/* 5. 同名多筆資料
	 * 路徑：/age?age=17&age=18&age=20
	 * 網址：http://localhost:8080/api/age?age=17&age=18&age=20
	 * 算出平均年齡
	 */
	@GetMapping(value = "/age", produces = "application/json;charset = utf-8")
	public ResponseEntity<ApiResponse<Object>> getAverage(
			// 多參數帶入 List 當中
			@RequestParam("age") List<String> ages) {
		
		if(ages == null || ages.size() == 0) {
			return ResponseEntity.badRequest().body(ApiResponse.error("輸入一個 age"));
		}
		
		// 計算平均，轉成 integer 在計算平均
		double avg = ages.stream().mapToInt(Integer::parseInt).average().orElseGet(()-> 0); 
		// 建立 data 資料，為 Map 資料型態，帶入 ApiResponse.success() 當中。
		Object map = Map.of("年齡", ages, "平均年齡", String.format("%.1f", avg));
		
		return ResponseEntity.ok(ApiResponse.success("計算成功", map));
	}
	
	
	/* 6. Lab 練習: 得到多筆 score 資料
	 * 路徑: "/exam?score=80&score=100&score=50&score=70&score=30"
	 * 網址: http://localhost:8080/api/exam?score=80&score=100&score=50&score=70&score=30
	 * 請自行設計一個方法，此方法可以
	 * 印出: 最高分=?、最低分=?、平均=?、總分=?、及格分數列出=?、不及格分數列出=?
	 */
	@GetMapping(value = "/score", produces = "application/json;charset = utf-8")
	public ResponseEntity<ApiResponse<Object>> scoreSummary(
			// 多參數帶入 List 當中
			@RequestParam("score") List<String> scores){
		
		if(scores == null || scores.size() == 0) {
			return ResponseEntity.badRequest().body(ApiResponse.error("輸入一個 score"));
		}
		
		// 計算最高分
		Integer max = scores.stream().mapToInt(Integer::parseInt).max().orElseGet(()-> 0); 
		// 計算最低分
		Integer min = scores.stream().mapToInt(Integer::parseInt).min().orElseGet(()-> 0); 
		// 計算平均
		Double avg = scores.stream().mapToInt(Integer::parseInt).average().orElseGet(()-> 0); 
		// 總分
		Integer sum = scores.stream().mapToInt(Integer::parseInt).sum().orElseGet(()-> 0); 
		// 及格分數
		List<Integer> pass = scores.stream().mapToInt(Integer::parseInt).filter(s -> s>60).toArray();
		// 不及格分數
		List<Integer> fail = scores.stream().mapToInt(Integer::parseInt).filter(s -> s<60).toArray();
		
		
	}// end of ScoreSummary
	
	
}
