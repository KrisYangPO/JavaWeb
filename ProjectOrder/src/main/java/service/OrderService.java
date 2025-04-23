package service;

import java.util.*;
import dao.OrderDAO;
import model.dto.OrderDTO;
import model.entity.Order;

// Service 用來呼叫 DAO
public class OrderService {
	private OrderDAO orderDAO = new OrderDAO();
	
	// 根據訂單項目 (item)，新增訂單，並回傳訂單顯示資訊(OrderDTO) 
	// Service 從 Servlet 取得 item 後，就可以建立 Order 物件，
	// 呼叫 OrderDAO 物件將他加入 orderDAO 的資料庫 (在這裡是 InMemory List)
	public OrderDTO addOrder(String item) {
		
		// 1. 根據訂單內容在 DAO 建立資訊
		Order order = new Order();
		order.setItem(item);
		order.setPrice(100); // 均一價 100
		// 傳給 OrderDAO 儲存
		orderDAO.save(order);
		
		// 2. 回傳訂單資訊 (藉由 OrderDTO)
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage(String.format("您點了:%s; 價格：&s 元", order.getItem(), order.getPrice()));
		return orderDTO;
	}
	
	// 取得歷史資料
	public List<OrderDTO> getOrderHistory(){
		// 取得所有資料
		List<Order> orders = orderDAO.findAll(); 
		// 將 List<Order> 轉成 List<OrderDTO>
		List<OrderDTO> orderDTOs = new ArrayList<>();
		
		// 一筆筆從 Order 轉成 OrderDTO
		for(Order order: orders) {
			OrderDTO dto = new OrderDTO();
			dto.setMessage(String.format("您點了:%s; 價格：&s 元", order.getItem(), order.getPrice()));
			orderDTOs.add(dto); // 逐筆資訊加入集合中。
		}
		// 將回報給 Controller: Servlet
		return orderDTOs;
	}
	
}
