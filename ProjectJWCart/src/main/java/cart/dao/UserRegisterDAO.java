package cart.dao;

import cart.model.entity.User;

public interface UserRegisterDAO {
	
	// 新增 User
	// int 回傳異動資料數量
	public abstract int addUser(User user);
	
	// email 驗證成功且修改：completed = true
	public abstract int emailConfirmOK(String username);

}
