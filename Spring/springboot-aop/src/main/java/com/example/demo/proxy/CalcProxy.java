package com.example.demo.proxy;

public class CalcProxy implements Calc{
	
	private Calc calc;
	
	public CalcProxy(Calc calc) {
		this.calc = calc;
	}

	@Override
	public Integer add(Integer x, Integer y) {
		if(x==null||y==null) {
			System.err.println("請輸入 x, y");
			return null;
		}
		
		return calc.add(x, y);
	}

	@Override
	public Integer div(Integer x, Integer y) {
		Integer result = null;
		
		// 前置通知
		System.out.println("請輸入整數，y 不得為0");
		
		if(x==null||y==null) {
			System.err.println("請輸入 x, y");
			return result;
		}
		
		try {
			result = calc.div(x, y);
			
		}catch (RuntimeException e) {
			System.out.println("錯誤發生" + e);
			
		}finally {
			// 後置通知
			System.out.println("結束計算");
		}
		
		return result;
	}

}
