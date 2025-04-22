package model;

import java.util.*;
import java.util.function.Predicate;

public class IceShop {
	// row data
	private String main;
	private String[] dressing;
	private int dressPriec = 10;
	private int totalPrice;
	
	// constructor
	public IceShop(String main, String[] dressing) {
		if (main != null || dressing != null) {
			this.main = main;
			this.dressing = dressing;
			// 計算 main + 每個 dressing 10 塊
			this.totalPrice = getPrice() + (dressPriec * dressing.length);
		}
	}
	
	// conditionOBJ
	// 將條件與達成結果用這個東西儲存
	public static class CheckMain{
		private int price;
		private Predicate<String> condition;
		
		public CheckMain(int price, Predicate<String> condition) {
			this.price = price;
			this.condition = condition;
		}
		
		public boolean check(String main) {
			return condition.test(main);
		}
		
		public int getResult() {
			return this.price;
		}
	}
	
	// conditionOBJ list
	public static final List<CheckMain> mainList = new ArrayList<>();
	static {
		mainList.add(new CheckMain(50, m -> m.equalsIgnoreCase("Ice")));
		mainList.add(new CheckMain(40, m -> m.equalsIgnoreCase("Douhua")));
	}
	
	// Stream
	public int getPrice() {
		return mainList.stream()
					   .filter(cm -> cm.check(main))
					   .findFirst()
					   .map(cm -> cm.getResult())
					   .orElse(0);
	}
	
	// getters
	public int getTotalPrice() {
		return totalPrice;
	}
	
	// 恢復名稱
	public static final Map<String, String> mainName = Map.of("Douhua", "豆花", "Ice","冰果");
	// 回傳中文字串
	public String getMain() {
		return mainName.get(main);
	}
	
	public String[] getDressing() {
		return dressing;
	}

}
