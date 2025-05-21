package com.example.demo.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 實體淚雨資料表對應 (自動建立資料表內容)
@Table(name = "users") // 可以手動建立資料表名稱，如果不寫就會以 class name 作為資料表名稱。
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id") // 資料表 user 中的預設欄位名稱
	private Integer userId; // 使用者 id
	
	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String username;
	
	@Column(name = "password_hash", nullable = false)
	private String password_hash;
	
	@Column(name = "salt", nullable = false)
	private String salt; 
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "role")
	private String role;
}
