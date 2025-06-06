package Case5;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.Tuple;
import smile.data.formula.Formula;
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
		
		double[] volumes = list.stream()
				.map(line -> line.split(",")[1])
				.mapToDouble(Double::parseDouble)
				.toArray();
		System.out.println(Arrays.toString(volumes));
		
		// 建立一個 DataFrame
		DataFrame data = DataFrame.of(DoubleVector.of("Price", prices));
		data = data.merge(DoubleVector.of("volume", volumes));
		
		// 要預測的變量
		Formula formula = Formula.lhs("Price");
		// 使用隨機森林建立回歸模型
		RandomForest model = RandomForest.fit(formula, data);
		
		// 獲取數據中的最後一條數據 (最後一天的價格與成交量)，已預測下一個值
		Tuple lastRow = data.stream().skip(data.nrows() -1 ).findFirst().orElse(null);
		
		// 取得預測價格
		double predicted = model.predict(lastRow);
		
		System.out.printf("預測今日收盤價：%.1f %n", predicted);
	}
}
