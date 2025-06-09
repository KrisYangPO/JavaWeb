package com.example.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class DynProxy {
	
	private Object object; 
	
	public DynProxy(Object object) {
		this.object = object;
	}
	
//	public Object getProxy() {
//		Object proxyObj = null;
//		
//		// newProxyInstance 需要載入這些東西：
//		// ClassLoader loader => 類別
//		ClassLoader loader = object.getClass().getClassLoader();
//		
//		// Class<?>[] interfaces => 被代理物件所實作介面
//		Class<?>[] interfaces = object.getClass().getInterfaces();
//		
//		// InvocationHandler handler => 處理代理的實現
//		InvocationHandler handler = (Object proxy, Method method, Object[] args) -> {
//			Object result = null; 
//			// 前置通知：
//			System.out.printf("前置通知-方法名稱：%s 方法參數:%s%n", method.getName(), Arrays.toString(args));
//			
//			try {
//				// 執行業務方法
//				result = method.invoke(object, args); // object: 被代理的物件; args: 方法參數
//				
//			} catch (Exception e) {
//				System.out.printf("前置通知-方法名稱：%s 方法參數:%s 例外訓詢：%s%n", method.getName(), Arrays.toString(args), e);
//				
//			} finally {
//				System.out.printf("後置通知-方法名稱：%s 方法參數:%s%n", method.getName(), Arrays.toString(args));
//			}
//			
//			return result;
//		};
//		
//		proxyObj = Proxy.newProxyInstance(loader, interfaces, handler);
//		return proxyObj;
//	}
//	
	
	
	// 當中有些前置通知，錯誤通知，事實上可以透過一個切面程式將所有訊息交給 MyAspect，
	// 事實上 MyAspect 就是一種 AOP，可以調用 AOP 就可以取得資訊。
	public Object getProxy() {
		Object proxyObj = null;
		
		// newProxyInstance 需要載入這些東西：
		// ClassLoader loader => 類別
		ClassLoader loader = object.getClass().getClassLoader();
		
		// Class<?>[] interfaces => 被代理物件所實作介面
		Class<?>[] interfaces = object.getClass().getInterfaces();
		
		// InvocationHandler handler => 處理代理的實現
		InvocationHandler handler = (Object proxy, Method method, Object[] args) -> {
			Object result = null; 
			// 前置通知：
//			System.out.printf("前置通知-方法名稱：%s 方法參數:%s%n", method.getName(), Arrays.toString(args));
			// 使用 AOP
			MyAspect.before(method, args);
			
			try {
				// 執行業務方法
				result = method.invoke(object, args); // object: 被代理的物件; args: 方法參數
				
			} catch (Exception e) {
//				System.out.printf("前置通知-方法名稱：%s 方法參數:%s 例外訓詢：%s%n", method.getName(), Arrays.toString(args), e);
				// 使用 AOP
				MyAspect.throwing(method, args, e);
				
			} finally {
//				System.out.printf("後置通知-方法名稱：%s 方法參數:%s%n", method.getName(), Arrays.toString(args));
				MyAspect.end(method, args);
			}
			
			return result;
		};
		
		proxyObj = Proxy.newProxyInstance(loader, interfaces, handler);
		return proxyObj;
	}


}
