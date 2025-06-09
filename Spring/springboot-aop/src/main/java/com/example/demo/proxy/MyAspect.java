package com.example.demo.proxy;

import java.lang.reflect.Method;
import java.util.Arrays;

// Aspect 切面程式：用來集中所有公用邏輯
// 這就是 AOP 的一種。
public class MyAspect {
	// 裡面就將 DynProxy 代理者裡的業務邏輯以外的錯誤前後置通知擷取，
	// 在這個 AOP 裡面執行，之後再由 DynProxy 呼叫 AOP 不同階段的方法。
	
	// 前置通知 (Advice)
	public static void before(Method method, Object[] args) {
		System.out.printf("前置通知-方法名稱：%s 方法參數:%s%n", method.getName(), Arrays.toString(args));
	}
	
	// 例外通知 (Advice)
	public static void throwing(Method method, Object[] args, Throwable e) {
		System.out.printf("前置通知-方法名稱：%s 方法參數:%s 例外訓詢：%s%n", method.getName(), Arrays.toString(args), e);
	}
	
	// 後置通知 (Advice)
	public static void end(Method method, Object[] args) {
		System.out.printf("後置通知-方法名稱：%s 方法參數:%s%n", method.getName(), Arrays.toString(args));
	}
}
