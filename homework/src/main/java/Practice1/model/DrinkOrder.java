package Practice1.model;

import java.util.*;

// 建立品項與價格對照表
public class DrinkOrder {
	// 原始資料屬性
	private String item;
	private String size; 
	private String addIce;
	private int price; 
	
	// 回報用的集合
	Map<String, String> checkAddIce = Map.of("yes", "加冰", "no", "不加冰"); 
	Map<String, String> checkSize = Map.of("S","小小", "M","中中", "L","大大");
	
	
	// constructor 
	// 直接在建構子計算 Price
	public DrinkOrder(String item, String size, String addIce) {
		this.item = item;
		this.size = size.toUpperCase();
		this.addIce = addIce.toLowerCase();
		
		// 計算 price
		DrinkTable DT = new DrinkTable();
		this.price = DT.sizeItemPriceTable().get(this.size).get(this.item);
	}
	
	// 回報訊息
	public String getInfo() {
		String itemText = this.item;
		String sizeText = checkSize.get(this.size);
		String addIceText = checkAddIce.get(this.addIce);
		
		return String.format("您點了一杯 %s（%s，%s），價格為 %d 元", itemText, sizeText, addIceText, this.price);
	}

	// getters for JSP
	public String getItem() {
		return item;
	}

	public String getSize() {
		return size;
	}

	public int getPrice() {
		return price;
	}

	public String getAddIce() {
		return checkAddIce.get(this.addIce);
	}
	
	
}// end of DrinkOrder
