package com.example.demo.model.dto;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//使用者憑證
// 登入成功後會得到憑證資料 (只有 Getter)
@Getter
@AllArgsConstructor
@ToString
public class UserCert {
	private Integer userId;
	private String username;
	private String role;
}
