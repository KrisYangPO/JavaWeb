package cart.service.impl;

import java.util.List;

import cart.dao.impl.ProductDAOImpl;
import cart.model.dto.ProductDTO;
import cart.model.entity.Product;
import cart.service.ProductService;
import cart.util.Entity2DTO;
import cart.dao.impl.*;
import cart.dao.*;


public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public void add(String productName, String price, String qty, String producImageBase64) {
		// 建立 Product 物件，並透過 DAO 物件將 Product 物件儲存在資料庫中
		Product product = new Product();
		product.setProductName(productName);
		product.setPrice(Integer.parseInt(price));
		product.setQty(Integer.parseInt(qty));
		product.setImageBase64(producImageBase64);
		
		// 加入資料庫
		try {
			productDAO.add(product);
			
		}catch(RuntimeException sq) {
			sq.getMessage();
		}
	}
	
	// 顯示所有結果
	@Override
	public List<ProductDTO> findAllProducts() {
		// 用 DAO 物件呼叫所有 Product List
		List<Product> products = productDAO.findAllProducts();
		
		// 用串接程式將 Entity 轉成 DTO 物件
		return products.stream()
				.map(Entity2DTO::productTransferToDTO)
				.toList();
	}

	// 刪除東西。
	@Override
	public void delete(Integer productId) {
		// 刪除資料庫
		try {
			productDAO.delete(productId);
			
		}catch(RuntimeException sq) {
			sq.getMessage();
		}
	}
	
}
