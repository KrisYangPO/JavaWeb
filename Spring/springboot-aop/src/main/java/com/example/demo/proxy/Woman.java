package com.example.demo.proxy;

public class Woman implements Person{

	@Override
	public void work() {
		
//		System.out.println("出門戴口罩"); // 每個物件都可能會有一樣的 "公用邏輯"
		
		// 每個物件不同的商業邏輯
		System.out.println("Woman 市場買菜");
		
//		System.out.println("回家戴口罩"); // 每個物件都可能會有一樣的 "公用邏輯"
	}
}
