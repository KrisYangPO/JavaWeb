package Case2;

import java.util.*;
import java.util.function.BiPredicate;

public class PhonePlan {
	// 原始數據
	private double min;
	private double traffic;
	
	// 主 class 建構子
	public PhonePlan(String min, String traffic) {
		this.min = Double.parseDouble(min);
		this.traffic = Double.parseDouble(traffic);
	}
	
	public PhonePlan(double min, double traffic) {
		this.min = min;
		this.traffic = traffic;
	}
	
	
	// 建立方案(建構商業規則專屬的 class)，
	public static class PhoneTraffic{
		private String plan;
		private String price;
		private BiPredicate<Double, Double> condition;
		
		// 建構子帶入回報字串與condition
		public PhoneTraffic(String plan, String price, BiPredicate<Double, Double> condition) {
			this.plan = plan;
			this.price = price;
			this.condition = condition;
		}
		
		// 判斷參數是否能夠讓 condition 為 true
		// 可以在後面 Stream 管線程式設計中，帶入 filter() 方法，
		// filter()需要給予 Predicate 物件，而這個方法可以回傳 condition 方法。
		public boolean checkPlan(double min, double traffic) {
			return this.condition.test(min, traffic);
		}
		
		// 顯示結果
		public String getPlan() {
			return String.format("%s($%s)", this.plan, this.price);
		}
	}
	
	// 建立陣列，搜集所有不同的 condition，根據每個 condition 給予不同方案名稱與價錢。
	public static final List<PhoneTraffic> plans = new ArrayList<>();
	
	// 初始化規則。
	static {
		plans.add(new PhoneTraffic("商務型", "1499", (min, traffic) -> {return min > 1000 || traffic > 50;}));
		plans.add(new PhoneTraffic("一般用戶型","660", (min, traffic) -> {return min > 500 && traffic > 10;}));
		plans.add(new PhoneTraffic("長輩節省型","220", (min, traffic) -> {return min <200 && traffic < 1;}));
	}
	
	// 利用 Stream 管線程式，根據 PhonePlan 得到的 min & traffic，
	// 判斷有沒有符合的方案，
	public String checkPlan() {
		return plans.stream()
				.filter(p -> p.checkPlan(min, traffic))
				.findFirst()
				.map(p -> p.getPlan())
				.orElse("查無方案");
	}
	// 在 filter() 當中可以看到，是一個 Predicate 格式，
	// 一個參數 p 帶入方法，在方法中要回傳 boolean 值， 
	// 而 Stream 物件元素就是 PhoneTraffic，每個 PhoneTraffic 實體都能執行 checkPlan
	// checkPlan 會根據物件實體所建立的 condition 還有 min, traffic 參數判斷符合與否，
	// 因此就會回傳布林值。這個設計就剛好符合 Predicate 格式，可以被帶入 filter 當中。
	
	// Getter
	public Double getMin() {
		return min;
	}
	
	public Double getTraffic() {
		return traffic;
	}
	
}
