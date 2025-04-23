package model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private String item;
	private Integer price;
	
	
}

/* Product 的格式與 Order 幾乎一致，
 * 原因是產品資訊與訂單必須要獨立開來，
 * 訂單已經成立，並且不會變，但是產品資訊可能會變。
 * 價錢可能會上漲，或是有其他變因，但是過去的訂單並不會因為產品改動而變，
 * 所以 Product 與 Order 必須獨立，未來價錢改變，不會影響過往訂單紀錄。
 */
