package cart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import cart.dao.UserRegisterDAO;
import cart.model.entity.User;

public class UserRegisterDAOImpl extends BaseDao implements UserRegisterDAO {

	@Override
	public int addUser(User user) {
		String sql = "insert into user(username, hash_password, hash_salt, email) values(?,?,?,?)";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getHashPassword());
			pstmt.setString(3, user.getHashsalt());
			pstmt.setString(4, user.getEmail());
			
			int rowcount = pstmt.executeUpdate();
			return rowcount;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// email 進行驗證，驗證成功的話，就修改這個 user 的 completed 資料改成 true，
	// 所以給予 username 直接將他的 completed 設定成 true
	// (到時後透過 EmailServlet 執行成功後，就會直接跳這個方法)
	@Override
	public int emailConfirmOK(String username) {
		String sql = "update user set completed = true where username = ?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, username);

			int rowcount = pstmt.executeUpdate();
			return rowcount;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	
}
