package model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
	private Integer id; // 序號
	private String text; // todo項目
	private boolean check; // 項目是否完成
	
	public boolean getCompleted() {
		// TODO Auto-generated method stub
		return check;
	}
}


/*
 DTO 的屬性與 Entity 需要一致，但是名稱不需要完全一致，
 因為 DTO 是從  DAO 資訊轉成的資料，在呈現給 Controller
 帶出去的資訊要與 Entity (SQL欄位) 一致，但是名稱不用完全一樣 -> 資安的小細節。
*/