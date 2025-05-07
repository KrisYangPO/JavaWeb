package case4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Grouping {
	
	public static void main(String[] args) {
		List<String> fruits = List.of("apple", "banana", "apple", "orange", "banana", "orange", "apple");
		
		// 要計算列表中，各個元素各有多少個數，
		// 傳統做法用forEach 遍歷所有列表元素，將其加入 Map 當中，value 就計算他遇到多少次。
		Map<String, Integer> counting  = new HashMap();
		
		for(String fruit: fruits) {
			if(!counting.containsKey(fruit)) {
				int count = 1;
				counting.put(fruit, count);
			}else {
				counting.put(fruit, counting.get(fruit) + 1);
			}
		}
		System.out.println(counting);
		
		
		// 使用 Grouping by:
		Map<String, Long> result = fruits.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(result);
		
		
		
		List<Map> students = List.of(
				Map.of("gender","男", "grade", "A"),
				Map.of("gender","男", "grade", "B"),
				Map.of("gender","男", "grade", "A"),
				Map.of("gender","女", "grade", "A"),
				Map.of("gender","女", "grade", "B"),
				Map.of("gender","男", "grade", "B")
				);
		
		// 用 gender 分組
		Map<String, Long> genderGroup = students
				.stream()
				.collect(Collectors.groupingBy(
						(m -> String.valueOf(m.get("gender"))), 
						Collectors.counting()
						)
						);
		System.out.println(genderGroup);
		
		
		// 用 grade 分組
		Map<String, Long> gradeGroup = students
				.stream()
				.collect(Collectors.groupingBy(
						(m -> String.valueOf(m.get("grade"))), 
						Collectors.counting()
						)
				);
		System.out.println(gradeGroup);
		
		
		
		
	}// end of Map
}
