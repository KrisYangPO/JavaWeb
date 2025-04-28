package cart.dao.impl;

import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cart.dao.UserListDAO;
import cart.model.entity.User;

public class UserListDAOImpl extends BaseDao implements UserListDAO {

	@Override
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<>();
		String sql = "select id, username, hash_password, hash_salt, email, completed from user order by id";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			
			// 讀取查詢的所有結果
			// next() 每次讀取一行資料
			// 如果沒有資料就會回傳 false
			while(rs.next()) {
				
				// 根據每列資訊建立一個 User 物件，再加入陣列
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setHashPassword(rs.getString("hash_password"));
				user.setHashsalt(rs.getString("hash_salt"));
				user.setEmail(rs.getString("email"));
				user.setCompleted(rs.getBoolean("completed"));
				
				// 加入陣列
				users.add(user);
			}
			return users;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
