package utils;
// 可以用來寫一些額外的小功能

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Utils {
	// 用方法判斷數值是否為數字：
	public static boolean isNumber(String data) {
		try {
			// 如果成功，就是數值，為 true。
			Integer.parseInt(data);
			return true;
			
		}catch(RuntimeException e){
			return false;
		}
		
		// 或是可以用正規表示法：
		//if(data == null) {return false;}
		//return data.matches("\\d+");
	}
	
	// 判斷是不是 Double
	public static boolean isDouble(String data) {
		try {
			// 如果成功，就是數值，為 true。
			Double.parseDouble(data);
			return true;
			
		}catch(RuntimeException e){
			return false;
		}
	}
	

}// end of class