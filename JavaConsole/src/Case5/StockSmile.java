package Case5;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import smile.data.DataFrame;
import smile.data.vector.DoubleVector;

public class StockSmile {
	public static void main(String[] args) throws Exception {
		// 利用簡單線性回歸來預測股價
		// 將 2330.txt 所有股價放到 double[] 中
		List<String> list = Files.readAllLines(Path.of("src/case06/2330.txt"));
		System.out.println(list);
		double[] prices = list.stream()
				.map(line -> line.split(",")[1])
				.mapToDouble(Double::parseDouble)
				.toArray();
		System.out.println(Arrays.toString(prices));
		
		// 建立一個 DataFrame
		DataFrame data = DataFrame.of(DoubleVector.of("Price", prices));
	}
}
