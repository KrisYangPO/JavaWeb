package model.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
	private Integer id; // 序號
	private String text; // todo項目
	private boolean completed; // 項目是否完成
}
