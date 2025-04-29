package cart.service;

import cart.model.dto.UserDTO;

public interface UserLoginService {
	
	// 驗證是否登入成功
	// username, password, authCode 就是登入時需要輸入的內容，
	// sessionAuthCode 是目前存在於畫面上的 authCode 內容 (要使用者輸入的 authcode)
	public abstract UserDTO login(String username, String password, String authCode, String sessionAuthCode);

}
