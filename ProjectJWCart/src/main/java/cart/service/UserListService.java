package cart.service;

import java.util.List;
import cart.model.dto.*;

public interface UserListService {
	
	// findAll User -> 建立列表
	public abstract List<UserDTO> findAllUsers();
}