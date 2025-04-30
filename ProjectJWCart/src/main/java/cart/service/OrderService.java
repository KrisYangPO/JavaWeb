package cart.service;

import java.util.List;
import cart.model.dto.*;

public interface OrderService {
	
	// 建立訂單
	// 購物車資料：List<ProductDTO> cart
	// 根據使用者 id 還有購物車資料下訂單
	public abstract void addOrder(Integer userId, List<ProductDTO> cart);
	
	// 查詢使用者訂單記錄
	public abstract List<OrderDTO> findAllOrdersByUserId(Integer userId);
}
