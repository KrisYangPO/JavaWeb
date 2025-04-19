package model;

import java.util.*;

public class CoffeeOrderMain {

	public static void main(String[] args) {
		Map<String, Integer> small = new LinkedHashMap<>();
		Map<String, Integer> medium = new LinkedHashMap<>();
		Map<String, Integer> large = new LinkedHashMap<>();
		Map<String, Map<String, Integer>> priceMap = new LinkedHashMap<>();
		
		String[] items = new String[] {"Latte", "Mocha", "Americano", "Cappuccino"};
		int[] sp = new int[] {50, 45, 40, 55};
		int[] mp = new int[] {70, 55, 45, 80};
		int[] lp = new int[] {90, 65, 60, 100};
		
		// 加入各個品項價錢
		for(int i=0; i<items.length; i++) {
			small.put(items[i], sp[i]);
		}
		
		for(int i=0; i<items.length; i++) {
			medium.put(items[i], mp[i]);
		}
		
		for(int i=0; i<items.length; i++) {
			large.put(items[i], lp[i]);
		}
		
		// 大小Map
		priceMap = Map.of("S", small, "M", medium, "L", large);
		System.out.println(priceMap);
		
	}// end of main
	
	
}// end of class
