package Case2;

import java.util.*;
import java.util.function.Predicate;

public class IceShop {
	// row data
	private String main;
	private String[] dressing;
	private int totalPrice; 
	
	// constructor
	public IceShop(String main, String[] dressing) {
		this.main = main;
		this.dressing = dressing;
		this.totalPrice = getPrice() + (10*dressing.length);
	}
	
	// conditionOBJ
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
	
	public String getMain() {
		return main;
	}
	
	public String[] getDressing() {
		return dressing;
	}

}
