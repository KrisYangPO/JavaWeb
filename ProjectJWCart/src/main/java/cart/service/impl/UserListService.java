package cart.service.impl;

import java.util.ArrayList;
import java.util.List;
import cart.dao.*;
import cart.dao.impl.UserListDAOImpl;
import cart.model.dto.*;

import cart.model.dto.UserDTO;
import cart.model.entity.User;
import cart.util.Entity2DTO;

public class UserListService implements cart.service.UserListService {
	
	private UserListDAO listdao = new UserListDAOImpl();

	@Override
	public List<UserDTO> findAllUsers() {
		// 呼叫 DAO 找出所有內容
		List<User> users = listdao.findAllUsers();		
		// 回傳 transfer 成 DTO 的物件，並搜集成集合。
		return users.stream().map(Entity2DTO::transferToDTO).toList();
	}
	
	
	// 將 Entity 轉成 dto
	private UserDTO transferToDTO(User user){
		UserDTO dto = new UserDTO(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				user.getCompleted()
				);
		
		return dto;
	}
	

}
