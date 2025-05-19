package com.example.demo.model.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
	@NotNull(message = "{roomDTO.roomId.notNull}")
	@Range(min=1, max=9999, message = "{roomDTO.roomId.range}")
	private Integer roomId;
	
	@NotNull(message = "{roomDTO.roomName.notNull}")
	@Range(min=2, message = "{roomDTO.roomName.range}")
	private String roomName;
	
	@NotNull(message = "{roomDTO.roomSize.notNull}")
	@Range(min=1, max=500, message = "{roomDTO.roomSize.range}")
	private Integer roomSize;
	
	/* roomDTOs 在輸入時需要進行驗證，確認不為空值且 roomSize 介在目標範圍之間。
	 * 利用 @NotNull 與 @Range 針對這個屬性做驗證。
	 * 驗證時，JSP <sp:errors path="roomSize" style="color: red" /> 就會進行驗證這個屬性。
	 * 在 Controller 會用 BindingResult 儲存驗證結果，讓 Controller 判斷結果。
	 * 
	 * */
	
}
