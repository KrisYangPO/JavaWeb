package dao;

import java.util.List;

import model.entity.Product;

public class ProductDAO {
	
	private static List<Product> products = List.of(
			new Product("牛肉麵", 150),
			new Product("番茄麵", 100),
			new Product("陽春麵", 60));
	
	public List<Product> findAll(){
		return products;
	}
	
	// 利用 item 取得單一 Product 物件，
	// DAO 儲存的 products 集合用 stream 取出後，
	// 用 Product 的 getItem()找尋匹配結果。
	// 找到後就回傳這個 Product 物件
	public Product getProduct(String item) {
		return products
				.stream()
				.filter(p->p.getItem().equals(item))
				.findAny()
				.orElseThrow();
	}
	
}
