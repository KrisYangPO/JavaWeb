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

	
	// 回圈讀取所有 Order，先建立 OrderDTO 物件，裡面有個 items 的 List<OrderItemDTO> 集合，
	// 再根據各個 Order 編號找出所有訂單明細 (item)，建立出來後也一樣先轉成 OrderItemDTO，
	// 將 OrderItemDTO 加入第一個回圈執行中的 OrderDTO 的 items 屬性集合中，
	
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
			
			
			// 將這個 order 底下的明細紀錄在 orderDTO 的 items 屬性裡。
			// 先用 order_id 呼叫這個 order 的所有 items，儲存在 items 的 List<OrderItem> 集合中，
			// 用 forEach 回圈將 List<OrderItem> 轉成 List<OrderItemDTO>
			// 建立好後，再直接紀錄到 orderDTO 的 items 屬性的集合中。
			
			List<OrderItem> items = orderDAO.findAllOrdersByOrderId(order.getOrderId());
			for(OrderItem item: items) {
				// OrderItem 轉 OrderItemDTO
				OrderItemDTO itemDTO = new OrderItemDTO();
				itemDTO.setItemId(item.getItemId());
				itemDTO.setOrderId(item.getOrderId());
				itemDTO.setProductId(item.getProductId());
				itemDTO.setQuantity(item.getQuantity());
				
				// 加入集合
				// 因為 orderDTO 已經初始化為 ArrayList，用 getItems() 就可以呼叫這個集合。
				// 直接用集合 add() 加入新的集合元素。
				orderDTO.getItems().add(itemDTO);
			
			}
		}
		return orderDTOs;
	}
	
}
