package cart.service;

import java.util.List;

import cart.model.dto.ProductDTO;
import cart.model.entity.Product;

public interface ProductService {
	// 新增一個 product 
	public abstract void add(String productName, String price, String qty, String producImageBase64);
	
	// 查詢所有 Products
	public abstract List<ProductDTO> findAllProducts();
	
	// 刪除特定 product id
	public abstract void delete(Integer productId);
}
