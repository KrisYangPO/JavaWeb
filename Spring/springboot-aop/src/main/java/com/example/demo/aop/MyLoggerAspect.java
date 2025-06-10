package com.example.demo.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // 被 Spring 作為 bean 管理
@Aspect // 切面程式
@Order(1) // 調用順序，不給的話就會是隨機
public class MyLoggerAspect {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/* 前置通知(Advice)
	 * value 說明要 "執行"哪個方法，其方法名稱要從 package 封裝名稱開始寫起，直到類別與方法名稱，
	 * 方法參數只需要宣告所有參數的資料型態就好。
	 * @Before(value = "execution(public Integer com.example.demo.proxy.CalcImpl.add(Integer, Integer))")
	 * 
	 * 如果不考慮參數，就在參數列寫：(..) 表示忽略參數資料型態，所以 *(..) 就表示所有方法且忽略參數格式。
	 * 如果不考慮 public Integer 也可以用 "*" 取代。
	 * 所有類別：用 "*" 取代。
	 * 如果加上 * 就表示在這個 package 裡的類別裡的所有參數列含有兩個 integer 的方法都可以被加入：@Before AOP
	 * 所以 CalcImpl 裡有 add(Integer x, Integer y) 還有 div(Integer x, Integer y) 都可以被使用。
	 */
//	@Before(value = "execution(public Integer com.example.demo.proxy.CalcImpl.*(Integer, Integer))")
//	public void before(JoinPoint joinPoint) {
//		String methodName = joinPoint.getSignature().getName();
//		Object[] args = joinPoint.getArgs();
//		String dateTime = sdf.format(new Date());
//		
//		// log 紀錄
//		System.out.printf("Log 前置通知：%s %s %s %n", dateTime, methodName, Arrays.toString(args));
//	}
	
	
	/* 建立切入點 @Pointcut，
	 * 類似把切面程式的程式呼叫(value)宣告好，簡化調用方法的程式。
	 * 另外 value 也支援邏輯運算: &&, ||, |
	 * 
		@Pointcut(value = "execution(public Integer com.example.demo.proxy.CalcImpl.add(Integer, Integer))")
		public void ptAdd() {}
		
		@Pointcut(value = "execution(public Integer com.example.demo.proxy.CalcImpl.div(Integer, Integer))")
		public void ptDiv() {}
		
	 * 就可以在切點寫：
	 * @Before(value = "ptAdd()") -> "execution(public Integer com.example.demo.proxy.CalcImpl.add(Integer, Integer))"
	 * 使用邏輯運算：
	 * @Before(value = "ptAdd() && ptDiv()")
	 * 
	 */
	@Pointcut(value = "execution(public Integer com.example.demo.proxy.CalcImpl.add(Integer, Integer))")
	public void ptAdd() {}
	
	@Pointcut(value = "execution(public Integer com.example.demo.proxy.CalcImpl.div(Integer, Integer))")
	public void ptDiv() {}
	
//	// 後置通知，無倫有沒有執行成功都會執行這個
//	@After(value = "ptDiv()")
//	public void endAdvice(JoinPoint joinPoint) {
//		String methodName = joinPoint.getSignature().getName();
//		Object[] args = joinPoint.getArgs();
//		String dateTime = sdf.format(new Date());
//		
//		// log 紀錄
//		System.out.printf("After 後置通知：%s %s %s %n", dateTime, methodName, Arrays.toString(args));
//	}
//	
//	// 正常返回通知
//	@AfterReturning(value = "ptDiv()", returning = "result")
//	public void AfterReturningAdvice(JoinPoint joinPoint, Object result) {
//		String methodName = joinPoint.getSignature().getName();
//		Object[] args = joinPoint.getArgs();
//		String dateTime = sdf.format(new Date());
//		
//		// log 紀錄
//		System.out.printf("正常返回通知：%s %s %s 回傳結果：%s%n", dateTime, methodName, Arrays.toString(args), result);
//	}
//	
//	// 異常返回通知
//	@AfterThrowing(value = "ptDiv()", throwing = "ex")
//	public void AfterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
//		String methodName = joinPoint.getSignature().getName();
//		Object[] args = joinPoint.getArgs();
//		String dateTime = sdf.format(new Date());
//		
//		// log 紀錄
//		System.out.printf("Throwing 異常通知：%s %s %s 回傳結果：%s%n", dateTime, methodName, Arrays.toString(args), ex);
//	}
	
	
	// 使用環繞通知，可以包含所有前置，正常，異常，後置通知
	// JoinPoint 要使用：ProceedingJoinPoint 
	@Around(value = "ptDiv()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
		Object result = null;
		String threadName = Thread.currentThread().getName();
		String methodName = joinPoint.getSignature().getName(); // 方法名稱
		Object[] args = joinPoint.getArgs(); // 方法參數
		String dateTime = sdf.format(new Date());
		
		// 前置通知
		System.out.printf("Log 前置通知：%s %s %s %n", dateTime, methodName, Arrays.toString(args));
		
		try {
			// 執行目標方法
			result = joinPoint.proceed();
			// 正常返回通知
			System.out.printf("AfterReturning 正常返回通知[%s]: %s %s 返回結果:%s %n", threadName, dateTime, methodName, result);
			
			return result;
			
		} catch (Throwable ex) {
			System.out.printf("Throwing 異常通知：%s %s %s 回傳結果：%s%n", dateTime, methodName, Arrays.toString(args), ex);
		
		}finally {
			System.out.printf("After 後置通知：%s %s %s %n", dateTime, methodName, Arrays.toString(args));
		}
		return null;
	}
	
}
