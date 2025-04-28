package cart.service;

public interface UserRegisterService {

	// 新增 User
	public abstract void addUser(String username, String password, String email);
	
	// email 驗證成功
	public abstract void emailConfirmOK(String username);
}
