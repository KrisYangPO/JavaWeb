package multiSelect;

import java.util.*;
import java.util.function.Predicate;

public class BMISelect {
	
	private String gender;
	private double height;
	private double weight;
	private double BMIvalue;
	
	// constructor
	public BMISelect(String g, double hei, double wei) {
		this.gender = g;
		this.height = hei;
		this.weight = wei;
		this.BMIvalue = this.weight/((this.height/100)*(this.height/100));
	}
	
	
	// BMI rules
	public static class CheckBMI{
		private double max;
		private double min;
		private Predicate<String> checkGender;
		private Predicate<Double> checkBmi;
		
		// constructor
		public CheckBMI(double min, double max, Predicate<String> chGen, Predicate<Double> chBmi) {
			this.max = max;
			this.min = min;
			this.checkGender = chGen;
			this.checkBmi = chBmi;
		}
		
		// check Gender:
		public boolean genderIdentify(String gender) {
			return checkGender.test(gender);
		}
		
		// checkBmi:
		public boolean reportBmicheck(double Bmi) {
			return checkBmi.test(Bmi);
		}
		
		// report whether pass
		public String reportResult() {
			return "Pass";
		}
		
		// report failed case:
		public String reportFail(double bmi) {
			if(bmi < min) {return "underweight";}
			else if(bmi > max) {return "overweight";}
			else {return "Fail BMI examinatino.";}
		}

	}//end of CheckBMI	

	
	// implement all conditions and collect them into a collection:
	public static final List<CheckBMI> BMIrules = new ArrayList<>();
	static {
		BMIrules.add(new CheckBMI(17.4, 23.3, g->g.equalsIgnoreCase("male"), bmi -> bmi > 17.4 && bmi < 23.3));
		BMIrules.add(new CheckBMI(17.1, 22.7, g->g.equalsIgnoreCase("female"), bmi -> bmi > 17.1 && bmi < 22.7));
	}
	
	
	// Stream Method:
	public String getBMIresult() {
		
		// 先建立 Optional 物件，後續給予 reportFail 狀況用：
		Optional<CheckBMI> examination = BMIrules.stream()
												 .filter(p -> p.genderIdentify(gender))
												 .findFirst();	
		
		// 取得性別配對結果，類似建立 Stream 管線流程分支點
		// 當今天 map 可以執行，p -> p.reportResult() 沒有問題，就可以離開 Stream，
		// 反之如果不行就執行 orElseGet()，從剛剛的分支點開始執行 fail cases。
		return examination
				.filter(p -> p.reportBmicheck(BMIvalue))
				.map(p -> p.reportResult())
				.orElseGet(() -> examination
								.map(p->p.reportFail(BMIvalue))
								.orElse("Something Wrong during processing."));
	}

	// getters
	public String getGender() {
		return gender;
	}

	public double getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public double getBMIvalue() {
		return BMIvalue;
	}
	//getters
	
	
}// end of BMISelect
