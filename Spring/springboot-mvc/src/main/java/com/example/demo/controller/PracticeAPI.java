package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BMI;
import com.example.demo.response.ApiResponse;

import com.example.demo.model.Book;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/* 同名多筆資料
	 * 路徑：/age?age=17&age=18&age=20
	 * 網址：http://localhost:8080/practice/age?age=17&age=18&age=20&age=28
	 * 算出平均年齡
	 */
	@GetMapping("/age")
	public ResponseEntity<ApiResponse<Double>> calAvg(@RequestParam(value = "age", required = false) List<Integer> ages){
		
		if(ages.isEmpty() || ages == null) {
			return ResponseEntity.badRequest().body(ApiResponse.error("參數錯誤沒有陣列。"));
		}
		
		Double avg = ages.stream().mapToInt(Integer::intValue).average().orElse(0.0);
		return ResponseEntity.ok(ApiResponse.success("計算成功", avg));
	}
	
	
	/* 多筆參數轉 Map
	 * name 書名(String), price 價格(Double), amount 數量(Integer), pub 出刊/停刊(Boolean)
	 * 路徑: /book?name=Math&price=12.5&amount=10&pub=true
	 * 路徑: /book?name=English&price=10.5&amount=20&pub=false
	 * 網址: http://localhost:8080/practice/book?name=Math&price=12.5&amount=10&pub=true
	 * 網址: http://localhost:8080/practice/book?name=English&price=10.5&amount=20&pub=false
	 * 讓參數自動轉成 key/value 的 Map 集合
	 * */
	@GetMapping("/book")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getBook(@RequestParam Map<String, Object> result){
		if(result.isEmpty() || result == null) {
			return ResponseEntity.badRequest().body(ApiResponse.error("無法取得書籍"));
		}
		return ResponseEntity.ok(ApiResponse.success("取得書籍", result));
	}
	
	
	/*
	 * 多筆參數轉指定 model (entity) 物件
	 * name 書名(String), price 價格(Double), amount 數量(Integer), pub 出刊/停刊(Boolean)
	 * 路徑: /book?name=Math&price=12.5&amount=10&pub=true
	 * 路徑: /book?name=English&price=10.5&amount=20&pub=false
	 * 網址: http://localhost:8080/practice/book2?name=Math&price=12.5&amount=10&pub=true
	 * 網址: http://localhost:8080/practice/book2?name=English&price=10.5&amount=20&pub=false
	 * 讓參數自動轉 成model (entity) 物件
	 * */
	@GetMapping("/book2")
	public ResponseEntity<ApiResponse<Object>> getBookEntity(@ModelAttribute Book book){
		if(book == null) {
			return ResponseEntity.badRequest().body(ApiResponse.error("書籍取得錯誤"));
		}
		return ResponseEntity.ok(ApiResponse.success("書籍取得成功", book));
	}
	
	
	/* 早期路徑參數設計：
	 * 路徑：/book?id=1 得到 id=1 的書，
	 * 路徑：/book?id=2 得到 id=2 的書，
	 * http://localhost:8080/api/book?id=1
	 * 
	 * REST 網頁狀態設計： 針對 URL 的風格設計的規格。
	 * GET	/books		查詢所有書籍
	 * GET	/book/1		查詢單筆書籍
	 * POST	/book		新增書籍
	 * PUT	/book/1		修改單筆書籍
	 * DELETE /book/1	刪除單筆書籍
	 * 
	 * 路徑：/book/1 得到 id=1 的書，
	 * 路徑：/book/2 得到 id=2 的書，
	 * 網址：http://localhost:8080/api/book/1
	 * 網址：http://localhost:8080/api/book/2
	 * */
	
	@GetMapping("/book/{id}")
	public ResponseEntity<ApiResponse<Book>> getBookId(
			@PathVariable(value = "id") Integer id){
		
		// 建立 InMemory List:
		List<Book> books = List.of(
				new Book(1, "鏈鋸人", 20.5, 10, true),
				new Book(2, "坂本日常", 30.0, 20, true),
				new Book(3, "進擊的巨人", 30.5, 40, true),
				new Book(4, "地獄樂", 10.5, 10, false)
				);
		
		// 根據 Id 找出特定書籍：
		Optional<Book> book = books
				.stream()
				.filter(b->b.getId().equals(id))
				.findFirst();
		
		// 確認裡面有沒有東西，Optional 物件可以儲存 null 值。
		if(book.isEmpty()) {
			return ResponseEntity.badRequest().body(ApiResponse.error("資料錯誤，無法查詢。"));
		}
		
		// Optional 物件如果有取得數值，要用.get() 將裡面的 value 取出來，
		// 另外用 getbook 儲存。
		Book getbook = book.get();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", getbook));
	}
	

	/*
	 * 利用 "路徑參數" 設計出可以只顯示出刊或停刊的設計風格
	 * 網址：http://localhost:8080/practice/book/pub/true
	 * 網址：http://localhost:8080/practice/book/pub/false
	 * */
	@GetMapping(value = "/book/pub/{pub}", produces = "application/json;charset = utf-8")
	// 因為可能會查詢到多筆資訊，所以以 List<Book> 作為回傳內容。
	public ResponseEntity<ApiResponse<List<Book>>> getBookPub(
			@PathVariable(value = "pub") Boolean pub){
		
		// 建立 InMemory List:
		List<Book> books = List.of(
				new Book(1, "鏈鋸人", 20.5, 10, true),
				new Book(2, "坂本日常", 30.0, 20, true),
				new Book(3, "進擊的巨人", 30.5, 40, false),
				new Book(4, "地獄樂", 10.5, 10, false)
				);
		
		// 根據 pub 出刊狀態，找出特定書籍：
		// 因為可能會查詢到多筆資訊，所以以 List<Book> 作為回傳內容。
		List<Book> book = books
				.stream()
				.filter(b->b.getPub().equals(pub))
				.toList();
		
		// 確認裡面有沒有查詢到非出刊/停刊以外的值。
		if(book.isEmpty()) {
			return ResponseEntity.badRequest().body(ApiResponse.error("資料錯誤，無法查詢。"));
		}
		
		return ResponseEntity.ok(ApiResponse.success("查詢成功:" + (pub? "出刊":"停刊"), book));
	}
}
