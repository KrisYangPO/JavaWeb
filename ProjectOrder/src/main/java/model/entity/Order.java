package model.entity;

import lombok.*;

// lombok 標籤直接參與 Compiling 過程，將你設定的標籤內容加入 class 檔，
// 所以 Java class 不用寫，寫標籤 lombok 就知道要在編譯過程中加入什麼結構，
// 像是 @Data 標籤就是告訴 lombok 在編譯過程中，
// 將所有屬性的 getter, setter, hashCode 還有 toString 加入 class 檔案，
// 這樣就不用直接在設計類別時宣告這些東西，
// Constructor 標籤也可以將建構子加入 class 檔。

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
	private String item;
	private Integer price; 

	
	
}// end of Order
