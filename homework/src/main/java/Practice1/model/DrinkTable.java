package Practice1.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DrinkTable {
	
	// constructor
	public DrinkTable() {}
	
	// row data
	List<String> items = new ArrayList<>(List.of("greenTea","blackTea","milkTea"));
	List<Integer> psmall = new ArrayList<>(List.of(30, 45, 40));
	List<Integer> pmedium = new ArrayList<>(List.of(50, 55, 45));
	List<Integer> plarge = new ArrayList<>(List.of(70, 65, 50));
	
	
	// 建立資料用的集合
	List<List<Integer>> allPrice = new ArrayList<>(List.of(psmall, pmedium, plarge));
	List<String> sizes = new ArrayList<>(List.of("S","M","L"));
	Map<String, Map<String, Integer>> sizeItemPrice;
	
	
	// 建立各個大小的 Price items
	public Map<String, Integer> itemPrice(List<String> itm, List<Integer> pr) {
		// 建立 item:price 的 map 集合
		Map<String, Integer> itemPriceMap = new LinkedHashMap<>();
		
		// 回圈建立這個 Map
		for(int i =0; i<itm.size(); i++) {
			itemPriceMap.put(itm.get(i), pr.get(i));
		}
		return itemPriceMap;
	}
	
	
	// 建立最後的表單，以大小編號為 Key: 以 itemPrice table 為 value
	// 裡面儲存每個 size (sizes.get(i))，size 裡各個品項在這個 size 的價錢 (itemPrice())
	public Map<String, Map<String, Integer>> sizeItemPriceTable() {
		
		// 回圈讀取每個 size，將每個 item 在這個 price 的 Map 蒐集到這個 size 的 key。
		for(int i = 0; i< sizes.size(); i++) {
			sizeItemPrice.put(sizes.get(i), itemPrice(items, allPrice.get(i)));
		}
		return sizeItemPrice;
	}
}
