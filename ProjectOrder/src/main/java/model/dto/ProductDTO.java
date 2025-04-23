package model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
	private String message; // 訂單結果資訊
	private Integer price;
}