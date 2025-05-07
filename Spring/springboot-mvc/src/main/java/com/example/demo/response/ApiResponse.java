package com.example.demo.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 建立 Server 與 Client 在資料傳遞上的統一結構與標準
public class ApiResponse<T> {
	
	private Integer status; // 狀態：200, 400
	private String message; // 訊息：查詢成功，新增成功...
	T data;	 // 實際資料 (payLoad)
	
	// 成功回應
	// 方法使用泛形時，要在方法回傳值資料型態前加上<T>，告訴使用者這個方法使用了泛形，
	// 後面 ApiResponse<T> 才時泛形方法的資料型態。
	public static <T> ApiResponse<T> success(String message, T data){
		return new ApiResponse<T>(200, message, data);
	}
	
	// 失敗回應
	public static <T> ApiResponse<T> error(int status, String message){
		return new ApiResponse<T>(status, message, null);
	}
}
