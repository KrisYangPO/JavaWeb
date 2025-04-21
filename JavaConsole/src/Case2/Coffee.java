package Case2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/*
 * 可擴充: add(...)
 * 邏輯可分離: 條件與邏輯分開
 * 容易測試
 * 可以利用 lambda 開發與理解上較為值觀
 * */
public class Coffee {
	private Double milk;
	private Double coffee;
	
	// 建立一個內部類來代表一種咖啡類型的判斷規則
	static class CoffeeRule {
		// 咖啡類型名稱
		private String type;
		// 咖啡描述
		private String description;
		// 條件的判斷邏輯
		private BiPredicate<Double, Double> condition;
		
		public CoffeeRule(String type, String description, BiPredicate<Double, Double> condition) {
			this.type = type;
			this.description = description;
			this.condition = condition;
		}
		
		// 符合比對
		public boolean matches(Double milk, Double coffee) {
			return condition.test(milk, coffee);
		}
		
		public String getResult() {
			return type + ": " + description;
		}
		
	}
	
	// 可以儲存所有咖啡規則的清單
	// final 限定變數 rules 是 ArrayList() 指向 Heap 記憶體內容，
	// 但是記憶體儲存的內容可以被修改，就是新增 ArrayList 元素內容。
	private static final List<CoffeeRule> rules = new ArrayList<>();
	
	// 類別資料初始化區段
	static {
		// 初始化規則
		rules.add(new CoffeeRule("濃郁的拿鐵", "牛奶的比例遠高於咖啡，味道偏向牛奶。", 
				(milk, coffee) -> milk >= 3 * coffee));
		rules.add(new CoffeeRule("平衡的拿鐵咖啡", "牛奶與咖啡的比例較為平衡，風味溫和。", 
				(milk, coffee) -> milk >= 1.5 * coffee));
		rules.add(new CoffeeRule("標準卡布奇諾", "牛奶與咖啡的比例恰到好處，典型的卡布奇諾", 
				(milk, coffee) -> milk.equals(coffee))); // milk == coffee
		rules.add(new CoffeeRule("濃縮咖啡", "咖啡的比例遠高於牛奶，口感濃烈，偏向純咖啡。", 
				(milk, coffee) -> coffee >= 3 * milk));
		rules.add(new CoffeeRule("普通咖啡", "牛奶與咖啡的比例較為普通，適合日常飲用。", 
				(milk, coffee) -> true));
	}
	
	// Coffee 建構子
	public Coffee(Double milk, Double coffee) {
		this.milk = milk;
		this.coffee = coffee;
	}
	
	// Coffee 建構子2
	public Coffee(String milk, String coffee) {
		this.milk = Double.parseDouble(milk);
		this.coffee = Double.parseDouble(coffee);
	}
	
	// 判斷咖啡類型
	public String getCoffeeType() {
		return rules.stream()
					.filter(rule -> rule.matches(milk, coffee)) // 找出符合條件的規則
					.findFirst()                                // 取得第一筆匹配的
					.map(CoffeeRule::getResult)                 // 傳換成字串
					.orElse("無法辨識的咖啡類型");
	}
	
	// 雙冒號：方法參考，
	// CoffeeRule::getResult 相當於：rule -> rule.getResult()
	
	
	// Getter
	public Double getMilk() {
		return milk;
	}
	
	public Double getCoffee() {
		return coffee;
	}
	
	// 上面使用 static 成員是因為那些東西只需要建立一次，而每個物件都可以取得那些 static 成員。
	
}