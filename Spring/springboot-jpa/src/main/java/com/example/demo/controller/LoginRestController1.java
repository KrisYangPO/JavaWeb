//package com.example.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import com.example.demo.exception.CertException;
//import com.example.demo.model.dto.UserCert;
//import com.example.demo.response.ApiResponse;
//import com.example.demo.service.CertService;
//import jakarta.servlet.http.HttpSession;
//
///* 網址參數傳遞方式整理：
// * @RequestParam：主要用在網址 url 的問號帶參數。/localhost:8081/test?username=00
// * @RequestBody: 主要是前端傳入 JSON 格式資料會用，但是要用相對應的 Entity 去接收。
// * 		假設今天前端傳入符合 RoomDTO 物件的資訊 (表單欄位一致)，在 Controller 也需要給用 RoomDTO 接收。
// * 
// * @PathVariable：網址地址本身會是參數，像是：
// *  1. /home/user1/
// *  2. /home/user2/ 
// *  3. /home/user3/
// *  當中 user1, user2, user3 在 controller 參數帶入就會是：
// *  @GetMapping("{user}")
// *  public test showUser(@PathVariable String user)
// * 
// */
//
//
////@RestController
////@RequestMapping(value = {"/rest"})
////// allowCredentials = "true" 允許接收客戶端傳來的憑證資料,例如: session id
////@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8002"}, allowCredentials = "true")
//public class LoginRestController1 {
//	
//	@Autowired
//	private CertService certService;
//	
//	// 執行登入：需要建立
//	// 執行登入時，會使用 certService 建立 userCert 物件，建立成功說明姓名密碼驗證成功，
//	// 並回傳一個 UserCert 物件，這個東西就會放入 session 當中，作為後續判斷使用者有沒有登入的狀態。
//	//
//	@PostMapping("/login")
//	public ResponseEntity<ApiResponse<UserCert>> login(@RequestParam String username, @RequestParam String password, HttpSession session) throws CertException{
//		try {
//			//  執行登入。
//			UserCert cert = certService.getCert(username, password);
//			session.setAttribute("userCert", cert);
//			return ResponseEntity.ok(ApiResponse.success("登入成功", null));
//		
//		}catch(CertException e) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error(401, "登入失敗。"));
//		}
//	}
//	
//	// 執行登出。
//	@GetMapping("/logout")
//	public ResponseEntity<ApiResponse<Void>> logout(HttpSession session){
//		// 如果 session 中沒有 userCert 的物件資料，就表示根本沒有登入，所以不能執行登出。
//		if(session.getAttribute("userCert") == null) {
//			return ResponseEntity
//	                .status(HttpStatus.UNAUTHORIZED)
//	                .body(ApiResponse.error(401, "登出失敗: 尚未登入 "));
//		}
//		
//		// 執行 Logout 就是將 Session 當中的所有內容刪除。
//		session.invalidate();
//		return ResponseEntity.ok(ApiResponse.success("登出成功，重新登入。", null));
//		
//	}
//	
//	// 執行確認：回報目前登入狀態的布林值。
//	@GetMapping("/check-login")
//	public ResponseEntity<ApiResponse<Boolean>> checkLogin(HttpSession session){
//		Boolean checkLogin = session.getAttribute("userCert") != null;
//		return ResponseEntity.ok(ApiResponse.success("檢查登入狀態。", checkLogin));
//	}
//	
//}
