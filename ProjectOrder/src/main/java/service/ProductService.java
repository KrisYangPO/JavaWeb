package service;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import model.dto.ProductDTO;
import model.entity.Product;

public class ProductService {
	private ProductDAO productDAO = new ProductDAO();
	
	// 將 productDAO 所儲存的 product 物件集合儲存成 productDTO 的資訊。
	public List<ProductDTO> findAll(){
		List<Product> products = productDAO.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		// 取得 Product 物件後，可以從 Product 物件存取出 item 名稱與金額，
		// 用這兩個參數建立出 ProductDTO 物件，並儲存在 productDTOs 集合裡。
		for(Product pr: products) {
			productDTOs.add(new ProductDTO(pr.getItem(), pr.getPrice()));
		}
		return productDTOs;
	}
	
	// 利用 item 名稱取得 productDTO.
	public ProductDTO getProductDTO(String item) {
		Product product = productDAO.getProduct(item);
		return new ProductDTO(product.getItem(), product.getPrice());
	}
	
	// 根據 Message 取得價格：
	// 這裡的 Message 是 OrderDTO 物件的屬性，
	// 經過 OrderService.addOrder(item) 可以存取 Order 物件，
	// 也會回報 OrderDTO 物件出來，裡面就包含 Order 物件的
	public Integer getPrice(String message) {
		return productDAO
				.findAll()
				.stream()
				.filter(p -> message.contains(p.getItem())) // 模糊比對
				.findFirst()
				.get()
				.getPrice();
	}
}
