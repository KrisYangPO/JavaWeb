package cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import cart.dao.OrderDAO;
import cart.dao.impl.OrderDAOImpl;
import cart.model.dto.OrderDTO;
import cart.model.dto.OrderItemDTO;
import cart.model.dto.ProductDTO;
import cart.model.entity.Order;
import cart.model.entity.OrderItem;
import cart.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDAO = new OrderDAOImpl();
	
	@Override
	public void addOrder(Integer userId, List<ProductDTO> cart) {
		// 預設數量 (Homework：如何調整數量)
		Integer quantity =1; 
		
		// 新增訂單主檔後得到 OrderID
		// 取得 userId 和他購物車下單的所有產品後：
		// (1.) 建立 Order 物件，根據 UserID 建立出一個訂單對應使用者的物件。
		Integer orderId = orderDAO.addOrder(userId);
		
		// (2.) 根據 Order 編號，產品編號，庫存，逐一新增訂單紀錄
		// 還要取得每個 Product 的 id
		for(ProductDTO productDTO: cart) {
			// orderId 是不變的，因為是同一個使用者下的訂單，
			// quantity 是固定的下單數量，之後需要扣掉庫存。
			orderDAO.addOrderItem(orderId, productDTO.getProductId(), quantity);
		}
	}

	
	@Override
	public List<OrderDTO> findAllOrdersByUserId(Integer userId) {
		List<OrderDTO> orderDTOs = new ArrayList<>();
		
		// 取得該使用者的訂單主檔資訊
		List<Order> orders = orderDAO.findAllOrdersByUserId(userId);
		for(Order order: orders) {
			// 訂單 DTO 做 mapping
			OrderDTO orderDTO = new OrderDTO();
			// 訂單資訊：
			orderDTO.setOrderId(order.getOrderId());
			orderDTO.setUserId(order.getUserId());
			orderDTO.setOrderDate(order.getOrderDate());
			
			// 明細
			for(OrderItem item: orderDAO.findAllOrdersByOrderId(order.getOrderId())) {
				// OrderItem 轉 OrderItemDTO
				OrderItemDTO itemDTO = new OrderItemDTO();
				itemDTO.setItemId(item.getItemId());
				itemDTO.setOrderId(item.getOrderId());
				itemDTO.setProductId(item.getProductId());
				itemDTO.setQuantity(item.getQuantity());
				
				// 加入集合
				orderDTO.getItems().add(itemDTO);
			
			}
		}
		return orderDTOs;
	}
	
}
