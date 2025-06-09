package com.example.demo.proxy;

// 靜態代理
// 用來呼叫被代理的物件"他自己的商業邏輯"，並組合所有人的公用邏輯
public class PersonProxy implements Person {
	
	// 呼叫父類別，可以透過多行呼叫每個 Person 子類別
	private Person person;
	
	// 透過建構式將每個 Person 帶入：
	public PersonProxy(Person person) {
		this.person = person;
	}
	
	// 設計新的方法，呼叫，並“組合”各個物件的商業邏輯與共用邏輯：
	// 這樣就不用讓每個物件(person子類)的實作類別都需要在他們的商業邏輯方法裡寫公用邏輯
	// 因為如果有 100 個實作子類別，就需要重複寫 100 個公用邏輯。
//	public void work() {
//		System.out.println("出門戴口罩"); // 公用邏輯
//		// 呼叫特定物件(被代理者)的商業邏輯
//		person.work();
//		System.out.println("回家脫口罩"); // 公用邏輯
//	}
	
	// 公用邏輯又可以分為前置通知與後置通知
	// 前置通知通常會是在方法最上面，因為無論程式結果如何都會執行公用邏輯達成一個宣告
	// 而後置邏輯會在程式最下方執行，但是如果"商業邏輯"發生例外，這樣就會倒置
	public void work() {
		System.out.println("出門戴口罩"); // 公用邏輯
		
		// 呼叫特定物件(被代理者)的商業邏輯
		try{
			person.work();
		}catch (Exception e) {
			System.out.println("工作中發生例外：" + e);
		}finally {
			System.out.println("回家脫口罩"); // 公用邏輯
		}
	}
}
