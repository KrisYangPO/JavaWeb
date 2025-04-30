package cart.dao;

import java.util.List;

import cart.model.entity.Order;
import cart.model.entity.OrderItem;

public interface OrderDAO {
	
	// 建立訂單主檔: order 編號
	// 有使用者 id 就可以建立訂單主檔
	public abstract Integer addOrder(Integer userId);
	
	// 建立訂單明細
	public abstract void addOrderItem(Integer orderId, Integer productId, Integer quantity);
	
	// 查詢"使用者訂單"明細 by UserId
	public abstract List<Order> findAllOrdersByUserId(Integer userId);
	
	// 查詢"訂單"明細 by OrderId
	public abstract List<OrderItem> findAllOrdersByOrderId(Integer orderId);
	
}
