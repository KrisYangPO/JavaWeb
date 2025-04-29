package cart.util;

import cart.model.dto.ProductDTO;
import cart.model.dto.UserDTO;
import cart.model.entity.Product;
import cart.model.entity.User;

public class Entity2DTO {
	
	// User
	// 將 Entity 轉成 dto
	public static UserDTO transferToDTO(User user){
		UserDTO dto = new UserDTO(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				user.getCompleted()
				);
		
		return dto;
	}
	
	
	// Product 
	//  將 Entity 轉成 dto
	public static ProductDTO productTransferToDTO(Product product){
		int total = product.getPrice()*product.getQty();
		
		// 建立 DTO 物件
		ProductDTO dto = new ProductDTO(
				product.getProductId(),
				product.getProductName(),
				product.getPrice(),
				product.getQty(),
				product.getImageBase64(),
				total);
		
		return dto;
	}
}
