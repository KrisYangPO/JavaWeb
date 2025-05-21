package com.example.demo.model.dto;

import lombok.Data;

// UserDto 對應 User Entity
// 屬性名稱可以與所對應的 entity 不同。
@Data
public class UserDto {
	private Integer userId;
	private String username;
	private String email;
	private Boolean active;
	private String role;
}
