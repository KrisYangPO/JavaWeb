package Case1;

import java.util.LinkedHashMap;
import java.util.Map;

public class CoffeeOrder {
	private String item; 
	private String size;
	private boolean addSugar;
	private int price;
	
	// Constructor
	public CoffeeOrder(String item, String size, boolean addSugar) {
		this.item = item;
		this.size = size;
		this.addSugar = addSugar;
		
		// 找出這個需求的 price:
		this.price = ItemPrice().get(size).get(item);
	}
	
	
	// 回報用的 Map:
	private static final Map<String, String> sizeTable = Map.of("S","小","M","中","L","大");
	private static final Map<String, String> sugarTable = Map.of("true","加糖","false", "不加糖");
	
	
	// 建立個大小 item:price
	Map<String, Integer> small = new LinkedHashMap<>();
	Map<String, Integer> medium = new LinkedHashMap<>();
	Map<String, Integer> large = new LinkedHashMap<>();
	Map<String, Map<String, Integer>> priceMap = new LinkedHashMap<>();
	
	
	// 建立固定的 item:price
	public Map<String, Map<String,Integer>> ItemPrice() {
		String[] items = new String[] {"Latte", "Mocha", "Americano", "Cappuccino"};
		int[] sp = new int[] {50, 45, 40, 55};
		int[] mp = new int[] {70, 55, 45, 80};
		int[] lp = new int[] {90, 65, 60, 100};
		
		// 加入各個品項價錢
		for(int i=0; i<items.length; i++) {
			small.put(items[i], sp[i]);
		}
		
		for(int i=0; i<items.length; i++) {
			medium.put(items[i], mp[i]);
		}
		
		for(int i=0; i<items.length; i++) {
			large.put(items[i], lp[i]);
		}
		
		return Map.of("S", small, "M", medium, "L", large);
	}
	
//	
//	public String getInfor() {
//		
//	}


}//  end of class
