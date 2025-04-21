package model;

import java.util.Map;

public class MakeCoffee {
	private double milk;
	private double coffee;
	private double ratio;
	private String kind;
	private String desc;
	
	// constructor
	public MakeCoffee(String milk, String coffee) {
		this.milk = Double.parseDouble(milk);
		this.coffee = Double.parseDouble(coffee);
		// 計算比例
		this.ratio = this.milk/this.coffee;
		
		// 設定 kind
		setKind();
		// 設定 description
		setDescription();
	}
	
	// 設定 kind
	private void setKind() {
		String kind = "";
		int ratio2 = (int)this.ratio * 10;
		Map<Integer, String> typeTable = Map.of(
				30,"濃郁的拿鐵",
				20,"平衡的拿鐵咖啡",
				10,"標準卡布奇諾",
				3,"濃縮咖啡");
		
		if(typeTable.get(ratio2) != null) {
			kind = typeTable.get(ratio2);
		}else {
			kind = "普通咖啡";
		}
		this.kind = kind;
	}
		
	// 設定 description
	private void setDescription() {
		String des = "";
		
		switch(kind) {
			case "濃郁的拿鐵":
				des="牛奶的比例遠高於咖啡，味道偏向牛奶。";
				break;
			case "平衡的拿鐵咖啡":
				des="牛奶與咖啡的比例較為平衡，風味溫和。";
				break;
			case "標準卡布奇諾":
				des="牛奶與咖啡的比例恰到好處，典型的卡布奇諾。";
				break;
			case "濃縮咖啡":
				des="咖啡的比例遠高於牛奶，口感濃烈，偏向純咖啡。";
				break;
			case "普通咖啡":
				des="牛奶與咖啡的比例較為普通，適合日常飲用。";
				break;
		}
		// 將方法結果傳入 this.desc.
		this.desc = des;
	}
	
	
	
	
}// end of makeCoffee
