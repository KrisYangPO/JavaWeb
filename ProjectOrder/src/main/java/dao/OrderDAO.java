package dao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import model.entity.Order;


public class OrderDAO {
	
	// 用 List 模擬資料庫 (InMemoryDatabase)
	private static List<Order> orders = new CopyOnWriteArrayList<>();
	
	// 存入資料
	public void save(Order order) {
		orders.add(order);
	}
	
	// 取得所有資料
	public List<Order> findAll(){
		return orders;
	}
	
}
