package model;

// 用一個物件儲存表單資料
public class User {
	
	// 跟表單一致的屬性
	private String userName;
	private String gender;
	private int age;
	private double weight;
	private double height;
	private double bmiValue;
	private String BMIresult;
	
	// 建構子封裝
	public User(String user, String gen, String age, String wei, String hei) {
		this.userName = user;
		this.gender = gen;
		this.age= Integer.parseInt(age);
		this.weight=Double.parseDouble(wei);
		this.height=Double.parseDouble(hei);
		// 計算 bmi
		this.bmiValue = this.weight / ((this.height/100) * (this.height/100));
		
		// 要透過 Servlet 將物件傳給 JSP，並呼叫 getters，
		// 但是 setter 不會被 JSP 執行，所以直接在建構式初始化直接計算，
		// 這樣 BMIresult 在建構式初始化時就會計算完成。
		setEvaluateBMI();
	}

	// 方法封裝
	public String getUserName() {
		return userName;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public double getWeight() {
		return weight;
	}

	public double getHeight() {
		return height;
	}

	public double getBmiValue() {
		return bmiValue;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", gender=" + gender + ", age=" + age + ", weight=" + weight + ", height="
				+ height + ", bmiValue=" + bmiValue + "]";
	}
	
	public String getEvaluateBMI() {
		return BMIresult;
	}
	
	// 判斷 BMI 
	private void setEvaluateBMI() {
		// 直接用 switch 設定男女的 Bmi 上下限，
		// 在判斷這個男女的 bmi 是否超過上下限。
		// 要用 this.BMIresult 指向這個方法計算完的區域變數 result 內容。
		String result;
		double min =0, max = 0;
		
		switch(this.gender) {
			case "male":
				min = 17.4;
				max = 23.3;
				break;
				
			case "female":
				min = 17.1;
				max = 22.7;
				break;
		}
		if (bmiValue > max) {result="體重過重";}
		else if (bmiValue < min){result="體重過輕";}
		else {result="體重正常";}
		
		this.BMIresult = result;
	}

}// end of User
