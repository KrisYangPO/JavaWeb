package cart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cart.model.entity.User;
import cart.dao.UserLoginDAO;

public class UserLoginDAOImpl extends BaseDao implements UserLoginDAO {

	@Override
	public User findUserByName(String username) {
		String sql = "select id, username, hash_password, hash_salt, email, completed from user where username = ?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			// 帶入 username
			pstmt.setString(1, username);
			
			// 執行查詢
			try(ResultSet rs = pstmt.executeQuery()){
				// 判斷是否有誤：
				if(rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setHashPassword(rs.getString("hash_password"));
					user.setHashsalt(rs.getString("hash_salt"));
					user.setEmail(rs.getString("email"));
					user.setCompleted(rs.getBoolean("completed"));
					
					return user;
				}
			}// 因為 ResultSet try 出問題也會是 SQLException，所以不用再次執行。
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
