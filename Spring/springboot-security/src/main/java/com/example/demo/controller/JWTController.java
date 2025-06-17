package com.example.demo.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.study.security.KeyUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/jwt")
public class JWTController {
	
	private static String signingSecret = "12393847281938475930292929292929";
	
	// 使用者登入後，建立驗證。
	@PostMapping("/issue")
	public String issueToken(@RequestBody Map<String, String> req) throws Exception{
		String username = req.get("username");
		String password = req.get("password");
		String phone = req.get("phone");
		String email = req.get("email");
		
		// 1. 自行判斷 username 與 password 是否正確(登入程序)？
		
		// 2. 當登入成功之後就可以發行 JWT 
		// JWT: 建立資料主體(之後要進行簽名的部分)
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject("報名 Java") // 主題
				.issuer("https://edu.cdri.org.tw") // 發行單位
				.claim("name", "John") // 額外的資料
				.claim("phone", "0912345678") // 額外的資料
				.claim("email", "John@gmail.com") // 額外的資料
				.build();
		
		// 將 claimsSet + 簽名 = token
		String token = KeyUtil.signJWT(claimsSet, signingSecret);
		return token;
	}
	
	
	// 驗證 JWT token
	// Authorization: Bearer xxx.xxx.xxx (在真正的 token 位置在 index = 7 以後的字串)
	@GetMapping("/verify")
	public Map<String, String> verifyToken(@RequestHeader("Authorization") String authHeader) throws ParseException {
		// 在真正的 token 位置在 index = 7 以後的字串
		String token = authHeader.substring(7);
		
		// 驗證 Token(JWT)
		if(KeyUtil.verifyJWTSignature(token, signingSecret)) {
			System.out.println("驗證成功");
			// 讀取 Token(JWT) 中 Claims(Payload) 的資料
			JWTClaimsSet claims = KeyUtil.getClaimsFromToken(token);
			System.out.println("讀取 subject: " + claims.getSubject());
			System.out.println("讀取 issuer: " + claims.getIssuer());
			System.out.println("讀取 name: " + claims.getStringClaim("name"));
			System.out.println("讀取 phone: " + claims.getStringClaim("phone"));
			System.out.println("讀取 email: " + claims.getStringClaim("email"));
			// 驗證成功
			return Map.of("status", "success", 
					"subject", claims.getSubject(),
					"issuer", claims.getIssuer(),
					"name", claims.getStringClaim("name"),
					"phone", claims.getStringClaim("phone"),
					"email", claims.getStringClaim("email")
			);
			
		} else {
			System.out.println("驗證失敗");
			return Map.of("status", "failed");
		}
	}
	
}
