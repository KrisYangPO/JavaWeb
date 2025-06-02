package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.CertException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.CertService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest")
public class LoginRestController {
	
	@Autowired
	private CertService certService;
	
	
	// 執行登入
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Void>> login(
			@RequestParam String username, 
			@RequestParam String password,
			HttpSession session) throws CertException{
		
		// 登入建立 UserCert
		UserCert userCert = certService.getCert(username, password);
		// 儲存至 session
		session.setAttribute("userCert", userCert);
		// 回傳狀態
		return ResponseEntity.ok(ApiResponse.success("登入成功，歡迎使用者：" + username, null));
	}
	
	
	// 執行登出
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(HttpSession session) throws CertException{
		if(session.getAttribute("userCert") == null) {
			throw new CertException("目前沒有使用者登入，登出失敗。");
		}
		
		session.invalidate();
		return ResponseEntity.ok(ApiResponse.success("登出成功。", null));
	}
	
	
	// 驗證登入狀態：設計一個網址可以檢查網頁登入狀態
	@GetMapping("/check-login")
	public ResponseEntity<ApiResponse<Boolean>> checkLogin(HttpSession session){
		Boolean check = session.getAttribute("userCert") != null;
		return ResponseEntity.ok(ApiResponse.success("目前正在登入狀態。", check));
	}
	
	// 直接處理 Exception:
	@ExceptionHandler(CertException.class)
	public ResponseEntity<ApiResponse<Void>> getUserException(CertException e, HttpSession session){
		// 取得錯誤資訊：
		e.printStackTrace();
		String type = e.getClass().getSimpleName();
		String message = e.getMessage();
		
		// 登出 session
		session.invalidate();
		
		// 回傳錯誤訊息：
		return ResponseEntity
						.status(HttpStatus.UNAUTHORIZED)
						.body(ApiResponse.error(401, "登入失敗：" + type + "錯誤內容：" + message));
	}
}