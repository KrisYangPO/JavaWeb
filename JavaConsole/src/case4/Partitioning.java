package case4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// 根據 collectors.partitioningBy 功能建立 布林：數值的 Map 資料
// 將資料用布林值的方式切分開。

public class Partitioning {
	
	public static void main(String[] args) {
		
		List<Integer> scores = List.of(100, 90, 50, 20, 70);
		
		/* 
		進行及格分組：
		{true:[100, 90, 70],
		 false: [50, 20]}
		*/
		Map<Boolean, List<Integer>> result = scores.stream().collect(Collectors.partitioningBy(s -> s >=60));
		System.out.println(result);
	}
}
