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
	
	// 刪除一筆資訊
	public void remove(int index) {
		orders.remove(index);
	}
	
	// 修改資訊
	public void update(int index, Order newdata) {
		orders.set(index, newdata);
	}
	
	// 取得單筆資料
	// 修改時需要針對那一筆資料做修改，所以要先抓出那個物件
	public Order getOrder(int index) {
		return orders.get(index);
	}
	
}
