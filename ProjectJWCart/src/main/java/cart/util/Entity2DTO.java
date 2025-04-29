package cart.util;

import cart.model.dto.UserDTO;
import cart.model.entity.User;

public class Entity2DTO {
	
	// 將 Entity 轉成 dto
	public static UserDTO transferToDTO(User user){
		UserDTO dto = new UserDTO(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				user.getCompleted()
				);
		
		return dto;
	}
}
