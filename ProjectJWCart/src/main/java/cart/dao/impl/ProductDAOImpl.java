package cart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cart.dao.ProductDAO;
import cart.model.entity.Product;

public class ProductDAOImpl extends BaseDao implements ProductDAO {

	// 新增 Product 
	@Override
	public void add(Product product) {
		String sql = "insert into product(product_name, price, qty, image_base64) values(?,?,?,?)";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getPrice());
			pstmt.setInt(3, product.getQty());
			pstmt.setString(4, product.getImageBase64());
			
			int rowcount = pstmt.executeUpdate();
			if (rowcount < 1) {
				throw new RuntimeException("新增失敗");
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	// 顯示所有 Product
	@Override
	public List<Product> findAllProducts() {
		List<Product> products = new ArrayList<>();
		String sql = "select id, product_name, price, qty, image_base64 from product order by id";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			
			// 檢視每筆查詢資料庫的結果
			while(rs.next()) {
				// 根據資料庫內容建立每筆 product 物件，
				Product product = new Product();
				product.setProductId(rs.getInt("id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setQty(rs.getInt("qty"));
				product.setImageBase64(rs.getString("image_base64"));
				
				// 加入每筆 product 
				products.add(product);
			}
			return products;
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

	
	// 刪除 Product 
	@Override
	public void delete(Integer productId) {
		String sql = "delete from product where id=?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, productId);
			
			int rowcount = pstmt.executeUpdate();
			if(rowcount < 1) {
				throw new RuntimeException("刪除錯誤。");
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}

}
