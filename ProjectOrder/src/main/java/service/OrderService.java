package service;

import java.util.*;
import dao.OrderDAO;
import model.dto.OrderDTO;
import model.entity.Order;

// Service 用來呼叫 DAO
public class OrderService {
	private OrderDAO orderDAO = new OrderDAO();
	
	 /* addOrder 會執行兩件事：
	 1. 將訂單儲存至資料庫：
	 	根據訂單項目 (item)，新增訂單，並回傳訂單顯示資訊(OrderDTO) 
	 	Service 從 Servlet 取得 item 後，就可以建立 Order 物件，
	 	呼叫 OrderDAO.save() 方法將他加入 orderDAO 的資料庫 (在這裡是 InMemory List)，
	 	這個過程就會將訂單內容儲存在 DAO 資料庫中。
	 2. 回報訂單資訊：
		建立 orderDTO 物件，將資訊儲存在 DTO 物件裡的屬性 message (setMessage)
		並直接回傳設定好的 orderDTO 物件。
	 */
	public OrderDTO addOrder(String item) {
		
		// 1. 根據訂單內容在 DAO 建立資訊
		Order order = new Order();
		order.setItem(item);
		order.setPrice(100); // 均一價 100
		// 傳給 OrderDAO 儲存
		orderDAO.save(order);
		
		// 2. 回傳訂單資訊 (藉由 OrderDTO)
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage(String.format("您點了:%s; 價格：%s 元", order.getItem(), order.getPrice()));
		return orderDTO;
	}
	
	
	
	/* 取得歷史資料
		歷史資料就是 addOrder 將 Order 物件加入 DAO 資料庫後，
		orderDAO 物件方法：findAll() 可以回傳一個儲存 Order 物件的 List 集合，
		顯示歷史資料就是利用這個集合做顯示資訊，這裡要將 Order 轉成 OrderDTO 集合。
		
		為什麼不是直接用 Order 物件傳出去是因為 Order 或需有些商業邏輯不能被公開，
		所以透過 DTO 物件取出 Order 物件的部分公開資訊：order.getItem(), order.getPrice() 
		取出來後儲存在 DTO 的屬性：Message 當中 (setMessage 方法)，
		再將設定好的 DTO 一個個儲存在 orderDTO 陣列裡，就能透過這個型別做回傳。
	 */
	public List<OrderDTO> getOrderHistory(){
		// 取得所有資料
		List<Order> orders = orderDAO.findAll(); 
		// 將 List<Order> 轉成 List<OrderDTO>
		List<OrderDTO> orderDTOs = new ArrayList<>();
		
		// 一筆筆從 Order 轉成 OrderDTO
		for(Order order: orders) {
			OrderDTO dto = new OrderDTO();
			dto.setMessage(String.format("您點了:%s; 價格：%s 元", order.getItem(), order.getPrice()));
			orderDTOs.add(dto); // 逐筆資訊加入集合中。
		}
		// 將回報給 Controller: Servlet
		return orderDTOs;
	}
	
	
	
	// 根據 index 刪除訂單
	public OrderDTO removeOrder(int index) {
		orderDAO.remove(index);
		OrderDTO orderDTO = new OrderDTO();
		
		// 刪除時透過 DTO 將訊息交給前端。
		orderDTO.setMessage(String.format("index = %s, 資料刪除成功", index));
		
		return orderDTO;
	}
	
	// 根據 String 刪除訂單
	// 對 removeOrder 做 overloading
	public OrderDTO removeOrder(String index) {
		return removeOrder(Integer.parseInt(index));
	}
	
	
	// 修改單筆資料
	public OrderDTO updateOrder(int index, String newItem) {
		Order order = orderDAO.getOrder(index);
		// 將要修改的 Order  用 orderDAO.getOrder()抓取
		// 抓取出來的就是 Order 物件，所以可以在這裡對這個 Order 物件做修改。
		// 修改完再由 update 紀錄 List.set(index, newObj)。
		order.setItem(newItem);
		orderDAO.update(index, order);
		
		// 回報 orderDTO 內容
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage(String.format("index = %s, 資料修改成功", index));
		return orderDTO;
	}
	// 修改訂單 Overloading
	public OrderDTO updateOrder(String index, String newItem) {
		return updateOrder(Integer.parseInt(index), newItem);
	}

}
