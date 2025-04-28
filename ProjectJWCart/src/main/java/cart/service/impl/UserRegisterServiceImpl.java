package cart.service.impl;

import cart.service.UserRegisterService;
import cart.util.HashUtil;
import cart.model.entity.*;
import cart.dao.*;
import cart.dao.impl.*;

public class UserRegisterServiceImpl implements UserRegisterService {

	// 取得 DAO 實體
	private UserRegisterDAO userRegisterDAO = new UserRegisterDAOImpl();
	
	@Override
	public void addUser(String username, String password, String email) {
		try {
			String hashSalt = HashUtil.generateSalt(); //取得鹽
			String hashPassword = HashUtil.hashPassword(password, hashSalt); // 取得 hash 密碼
			
			// User 物件中，不可以存放明碼 Password 資料
			User user = new User();
			user.setUsername(username);
			user.setHashPassword(hashPassword);
			user.setHashsalt(hashSalt);
			user.setEmail(email);
			
			int rowcount = userRegisterDAO.addUser(user);
			if(rowcount < 1) {
				System.out.println("新增失敗");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void emailConfirmOK(String username) {
		if (username == null) {
			return;
		}
		int rowcount = userRegisterDAO.emailConfirmOK(username);
		if(rowcount < 1) {
			System.out.println("驗證失敗");
		}
	}

}
