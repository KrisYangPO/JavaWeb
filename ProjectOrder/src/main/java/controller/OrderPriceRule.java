package controller;

import java.util.*;
import java.util.function.Predicate;

public class OrderPriceRule {

	private String item;
	
	public OrderPriceRule(String item) {
		this.item = item;
	}
	
	// 條件與達成結果
	public static class PriceRule{
		// 達成結果與條件
		private int price;
		private Predicate<String> condition;
		
		// constructor 
		public PriceRule(int price, Predicate<String> condition) {
			this.price = price;
			this.condition = condition;
		}
		
		// Predicate method
		public boolean getResult(String item){
			return condition.test(item);
		}
		
		// return result
		public int reportResult() {
			return this.price;
		}
	}// end of Price Rule
	
	// 搜集所有條件
	public static final List<PriceRule> pricerules = new ArrayList<>();
	static {
		pricerules.add(new PriceRule(150, itm -> itm.equalsIgnoreCase("牛肉麵")));
		pricerules.add(new PriceRule(60, itm -> itm.equalsIgnoreCase("陽春麵")));
		pricerules.add(new PriceRule(100, itm -> itm.equalsIgnoreCase("番茄麵")));
	}
	
	// 回報滿足條件結果：
	public int getPrice() {
		return pricerules.stream()
				.filter(pr -> pr.getResult(item))
				.findFirst()
				.map(pr -> pr.reportResult())
				.orElse(0);
	}
	
	// getter
	public String getItem() {
		return item;
	}
	
	
}
