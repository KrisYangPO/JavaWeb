package com.example.demo.proxy;

public class Man implements Person{

	@Override
	public void work() {
		
//		System.out.println("出門戴口罩"); // 每個物件都可能會有一樣的 "公用邏輯"
		
		// 每個物件不同的商業邏輯，只屬於 work() 主要的內容。
		System.out.println("Man 去上班");
		
//		System.out.println("回家戴口罩"); // 每個物件都可能會有一樣的 "公用邏輯"
	}
	
}
