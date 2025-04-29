package cart.dao;

import java.util.List;

import cart.model.entity.Product;

public interface ProductDAO {
	
	// 新增一個 product 
	public abstract void add(Product product);
	
	// 查詢所有 Products
	public abstract List<Product> findAllProducts();
	
	// 刪除特定 product id
	public abstract void delete(Integer productId);
}

