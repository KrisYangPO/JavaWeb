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
		Integer orderId = orderDAO.addOrder(userId);
		
		// 逐一新增訂單紀錄
		// 還要取得每個 Product 的 id
		for(ProductDTO productDTO: cart) {
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
