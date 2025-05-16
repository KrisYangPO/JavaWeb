package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 代表 Java 物件實體，與資料表對應，彼此會進行對應 (自動建立資料表。)
@Table(name = "room") // 宣告表格名稱。若資料表與此物件實體一致，則不用設定此行。
public class Room {
	
	@Id // 主鍵序號
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // room_id 會從1開始自動生成，每次新增一筆資料就+1。
	@Column(name = "room_Id") // 設定欄位名稱與 sql 欄位特性。
	private Integer roomId;
	// 資料表的表格命名會以 room_Id 而物件實體則是 roomId，彼此牽連。
	
	@Column(name = "room_name", nullable = false, unique = true)
	private String roomName;
	
	// sql 語法設定預設值為 0
	@Column(name = "room_size", columnDefinition = "integer default 0")
	private Integer roomSize;
}

// 執行後，Sping 就會跟去這個實體與你所設定的資料建立資料表。
// JPA 因此可以在你設計 Entity 物件實體的過程中，在特定資料庫建立相對應的資料表。


