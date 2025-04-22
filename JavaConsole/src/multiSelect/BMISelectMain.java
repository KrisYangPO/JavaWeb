package multiSelect;

public class BMISelectMain {

	public static void main(String[] args) {
		BMISelect BMIs = new BMISelect("male", 174.5, 90.5);
		System.out.printf("BMI數值：%.2f, BMI 結果：%s",BMIs.getBMIvalue(), BMIs.getBMIresult());
	}

}
