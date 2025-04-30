package cart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import cart.dao.OrderDAO;
import cart.model.entity.Order;

public class OrderDAOImpl extends BaseDao implements OrderDAO {

	@Override
	public Integer addOrder(Integer userId) {
		// 使用反引號 `` 避免跟其他命令衝突
		String sql = "insert into `order` (user_id) values(?)";
		
		// order id 是 mySQL 自動生成。用來存放新增後的 order 數值。
		Integer orderId = null; 
		// 所以要加上 Statement.RETURN_GENERATED_KEYS 參數設定。
		// 可以取得 mySQL 自動生成的數值。
		try(PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			pstmt.setInt(1, userId);
			pstmt.executeUpdate(); // 執行更新
			
			// 取得 GENERATED KEYS:
			ResultSet generateKeys = pstmt.getGeneratedKeys();
			if(generateKeys.next()) {
				
				// 取得新增後，自動生成的 order_id (order table 的第一個欄位，是 mySQL 自動生成的)
				orderId = generateKeys.getInt(1);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return orderId;
	}

	@Override
	public void addOrderItem(Integer orderId, Integer productId, Integer quantity) {
		String sql = "insert into order_item(order_id, product_id, quantity) values(?,?,?)";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, quantity);
			
			pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
			// 新增失敗，就不要執行扣抵庫存的區塊，直接 return 就會離開方法。
			return; 
		}
		
		// 扣抵庫存 (買完東西，庫存需要刪除一樣的量)
		sql = "update product set qty = qty - ? where product_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, productId);
			
			pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	
	@Override
	public List<Order> findAllOrdersByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAllOrdersByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
