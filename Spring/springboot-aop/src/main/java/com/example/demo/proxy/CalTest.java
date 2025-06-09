package com.example.demo.proxy;

public class CalTest {

	public static void main(String[] args) {
		Calc calc = new CalcImpl();
		
		System.out.println(calc.add(1, 2));
	}

}
