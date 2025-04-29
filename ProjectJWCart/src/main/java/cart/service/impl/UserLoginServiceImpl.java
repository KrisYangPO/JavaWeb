package cart.service.impl;

import javax.management.RuntimeErrorException;

import cart.dao.UserLoginDAO;
import cart.dao.impl.UserLoginDAOImpl;
import cart.model.dto.UserDTO;
import cart.model.entity.User;
import cart.service.UserLoginService;
import cart.util.Entity2DTO;
import cart.util.HashUtil;

public class UserLoginServiceImpl implements UserLoginService{
	
	private UserLoginDAO userLoginDAO = new UserLoginDAOImpl();

	@Override
	public UserDTO login(String username, String password, String authCode, String sessionAuthCode) {
		// 比對驗證碼：
		if(!authCode.equals(sessionAuthCode)) {
			throw new RuntimeException("密碼不符");
		}
		User user = userLoginDAO.findUserByName(username);
		
		// 每有建立成功，資料庫沒這個人
		if(user == null) {
			throw new RuntimeException("查無此人");
		}
		
		// 確認 email 是否通過驗證
		boolean completed = user.getCompleted();
		if(!completed) {
			throw new RuntimeException("Email 還沒驗證。");
		}
		
		// 驗證密碼(password 來自登入時輸入)：
		try {
			String hashPassword = HashUtil.hashPassword(password, user.getHashsalt());
			boolean checkPassword = user.getHashPassword().equals(hashPassword);
			
			if(!checkPassword) {
				throw new RuntimeException("密碼錯誤");
			}
			
			// 驗證成功。
			// 將 user 轉成 DTO 物件
			return Entity2DTO.transferToDTO(user);
					
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	
}
