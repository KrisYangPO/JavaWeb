package com.example.demo.controller;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BMI;
import com.example.demo.model.Book;
import com.example.demo.response.ApiResponse;

// RestController 可以免去撰寫 @ResponseBody，
// 但若要透過 jsp 渲染則不適用。

@RestController() 
@RequestMapping("/api")
// @RequestMapping 統一這個 ApiController 下的所有 URL 都會有前綴：/api
// 所以後面的路徑其實是：/api/home/ 和 /api/
public class ApiController {
	
	// 可以將底下執行的程式的參數紀錄在外部檔案裡面
	// 要先設定 Logger 物件
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
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
		
		// 如果要紀錄參數到上面 Logger 物件的 log 紀錄檔案裡：
		logger.info("執行路徑是：/greet?name="+username + "&age=" + userage);
		// logger 就會將這段話紀錄到 logs 資料夾中的 app.log 檔案裡。
		// 原因是我們在 resources/applicatino.properties 當中設定 log 配置，
		// logging.file.name=logs/app.log 會將檔案儲存到這檔案裡。
		
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
//	@GetMapping(value = "/exam", produces = "application/json;charset = utf-8")
//	public ResponseEntity<ApiResponse<Object>> scoreSummary(
//			// 多參數帶入 List 當中
//			@RequestParam("score") List<String> scores){
//		
//		if(scores == null || scores.size() == 0) {
//			return ResponseEntity.badRequest().body(ApiResponse.error("輸入一個 score"));
//		}
//		
//		// 計算最高分
//		Integer max = scores.stream().mapToInt(Integer::parseInt).max().orElseGet(()-> 0); 
//		// 計算最低分
//		Integer min = scores.stream().mapToInt(Integer::parseInt).min().orElseGet(()-> 0); 
//		// 計算平均
//		Double avg = scores.stream().mapToInt(Integer::parseInt).average().orElseGet(()-> 0); 
//		// 總分
//		Integer sum = scores.stream().mapToInt(Integer::parseInt).sum(); 
//		// 及格分數
//		int[]  pass = scores.stream().mapToInt(Integer::parseInt).filter(s -> s>60).toArray();
//		// 不及格分數
//		int[] fail = scores.stream().mapToInt(Integer::parseInt).filter(s -> s<60).toArray();
//		
//		Object map = Map.of(
//				"所有成績", scores,
//				"最高成績", String.format("%s", max),
//				"最低成績", String.format("%s", min),
//				"平均年齡", String.format("%.1f", avg),
//				"總分", String.format("%s", sum),
//				"及格分數", pass,
//				"不及格分數", fail
//				);
//		
//		
//		return ResponseEntity.ok(ApiResponse.success("成績總覽", map));
//		
//	}// end of ScoreSummary
//	
	
	// 使用 partitioningBy 的寫法將成績及格與不及格分開。
	@GetMapping(value = "/exam", produces = "application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Object>> getExamInfo(@RequestParam(name = "score", required = false) List<Integer> scores) {
		
		// 統計資料
		// 統計物件：IntSummaryStatistics
		IntSummaryStatistics stat = scores.stream().mapToInt(Integer::intValue).summaryStatistics();
		
		// 利用 Collectors.partitioningBy 分組
		// key=true 及格分數 | key=false 不及格分數
		Map<Boolean, List<Integer>> resultMap = scores.stream()
				.collect(Collectors.partitioningBy(score -> score >= 60));
		
		// 建立 ApiResponse 回傳的 data 資料 (T)
		Object data = Map.of(
				"最高分", stat.getMax(),
				"最低分", stat.getMin(),
				"平均", stat.getAverage(),
				"總分", stat.getSum(),
				"及格", resultMap.get(true),
				"不及格", resultMap.get(false));
		
		return ResponseEntity.ok(ApiResponse.success("計算成功", data));
	}
	
	
	/*
	 * 7. 多筆參數轉 Map
	 * name 書名(String), price 價格(Double), amount 數量(Integer), pub 出刊/停刊(Boolean)
	 * 路徑: /book?name=Math&price=12.5&amount=10&pub=true
	 * 路徑: /book?name=English&price=10.5&amount=20&pub=false
	 * 網址: http://localhost:8080/api/book?name=Math&price=12.5&amount=10&pub=true
	 * 網址: http://localhost:8080/api/book?name=English&price=10.5&amount=20&pub=false
	 * 讓參數自動轉成 key/value 的 Map 集合
	 * */
	
	@GetMapping(value = "/book", produces = "application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Object>> getBookInfo(
			@RequestParam Map<String, Object> bookMap){
		return ResponseEntity.ok(ApiResponse.success("回應成功", bookMap));
	}
	
	
	/*
	 * 8. 多筆參數轉指定 model (entity) 物件
	 * 路徑：承上
	 * 網址：http://localhost:8080/api/book2?name=English&price=10.5&amount=20&pub=false
	 * */
	@GetMapping(value = "/book2", produces = "application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Object>> getBookInfo2(@RequestParam Book book){
		book.setId(1);
		System.out.println(book);
		return ResponseEntity.ok(ApiResponse.success("回應成功2", book));
	}
	
	
	/*
	 * 9. 路徑參數
	 * 早期路徑參數設計：
	 * 路徑：/book?id=1 得到 id=1 的書，
	 * 路徑：/book?id=2 得到 id=2 的書，
	 * http://localhost:8080/api/book?id=1
	 * 
	 * REST 網頁狀態設計： 針對 URL 的風格設計的規格。
	 * GET /books 查詢所有書籍
	 * GET /book/1 查詢單筆書籍
	 * POST /book 新增書籍
	 * PUT /book/1 修改單筆書籍
	 * DELETE /book/1 刪除單筆書籍
	 * 
	 * 路徑：/book/1 得到 id=1 的書，
	 * 路徑：/book/2 得到 id=2 的書，
	 * 網址：http://localhost:8080/api/book/1
	 * 網址：http://localhost:8080/api/book/2
	 * */
	@GetMapping(value = "/book/{id}", produces = "application/json;charset = utf-8")
	public ResponseEntity<ApiResponse<Book>> getBookById(
			@PathVariable(name = "id") Integer id){
		List<Book> books = List.of(
				new Book(1, "小叮噹", 12.5, 20, false),
				new Book(2, "老夫子", 10.5, 20, false),
				new Book(3, "好小子", 8.5, 40, true),
				new Book(4, "鏈鋸人", 14.5, 50, true)
		);
		
		// 根據傳入的 id 找到書櫃中的書。
		Optional<Book> optBook = books.stream().filter(b -> b.getId().equals(id)).findFirst();
		
		if(optBook.isEmpty()){
			return ResponseEntity.badRequest().body(ApiResponse.error("查無此書"));
		}
		
		// 取得 Optional<Book> optBook 所找到的書。
		Book book = optBook.get(); 
		return ResponseEntity.ok(ApiResponse.success("查詢成功", book));
	}
	
	
	/*
	 * 利用 "路徑參數" 設計出可以只顯示出刊或停刊的設計風格
	 * 網址：http://localhost:8080/api/book/pub/true
	 * 網址：http://localhost:8080/api/book/pub/false
	 * */
	@GetMapping(value = "/book/pub/{isPub}", produces = "application/json;charset = utf-8")
	public ResponseEntity<ApiResponse<List<Book>>> queryBook(
			@PathVariable(name = "isPub") Boolean isPub){
		// 書庫
		List<Book> books = List.of(
				new Book(1, "小叮噹", 12.5, 20, false),
				new Book(2, "老夫子", 10.5, 20, false),
				new Book(3, "好小子", 8.5, 40, true),
				new Book(4, "鏈鋸人", 14.5, 50, true)
		);
		
		List<Book> querybooks = books.stream().filter(book -> book.getPub().equals(isPub)).toList();
		
		if(querybooks.isEmpty()){
			return ResponseEntity.badRequest().body(ApiResponse.error("查無此書"));
		}
		
		// 取得 Optional<Book> optBook 所找到的書。
		return ResponseEntity.ok(ApiResponse.success("查詢成功" + (isPub?"出刊":"停刊"), querybooks));
	}
}
