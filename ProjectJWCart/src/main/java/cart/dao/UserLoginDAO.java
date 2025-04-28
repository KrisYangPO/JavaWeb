package cart.dao;

import cart.model.entity.User;

public interface UserLoginDAO {
	
	// 根據 Username 取得 User 物件
	public abstract User findUserByName(String username);
}
