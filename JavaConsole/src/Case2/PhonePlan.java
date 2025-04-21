package Case2;

import java.util.*;
import java.util.function.BiPredicate;

public class PhonePlan {
	
	private double min;
	private double traffic;
	
	public PhonePlan(String min, String traffic) {
		this.min = Double.parseDouble(min);
		this.traffic = Double.parseDouble(traffic);
	}
	
	public PhonePlan(double min, double traffic) {
		this.min = min;
		this.traffic = traffic;
	}
	
	
	// 建立方案，
	public static class PhoneTraffic{
		private String plan;
		private String price;
		private BiPredicate<Double, Double> condition;
		
		public PhoneTraffic(String plan, String price, BiPredicate<Double, Double> condition) {
			this.plan = plan;
			this.price = price;
			this.condition = condition;
		}
		// 判斷參數是否能夠讓 condition 為 true
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
	
	static {
		plans.add(new PhoneTraffic("商務型", "1499", (min, traffic) -> {return min > 1000 || traffic > 50;}));
		plans.add(new PhoneTraffic("一般用戶型","660", (min, traffic) -> {return min > 500 && traffic > 10;}));
		plans.add(new PhoneTraffic("長輩節省型","220", (min, traffic) -> {return min <200 && traffic < 1;}));
	}
	
	public String checkPlan() {
		return plans.stream()
				.filter(p -> p.checkPlan(min, traffic))
				.findFirst()
				.map(p -> p.getPlan())
				.orElse("查無方案");
	}
	
	// Getter
	public Double getMin() {
		return min;
	}
	
	public Double getTraffic() {
		return traffic;
	}
	
}
