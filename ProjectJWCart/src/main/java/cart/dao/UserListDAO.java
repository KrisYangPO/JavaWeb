package cart.dao;

import java.util.List;
import cart.model.entity.*;

public interface UserListDAO {
	
	// findAll User -> 建立列表
	public abstract List<User> findAllUsers();
}
