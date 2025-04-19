package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/BMI")
public class BMIServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設定編碼，對 Servlet 跟瀏覽器都要同時設定。
		// 對 Servlet 設定編碼
		resp.setCharacterEncoding("UTF-8");
		// 對瀏覽器通知編碼：
		resp.setContentType("text/html; charset=UTF-8");
		
		// BMI 產生器：
		String height = req.getParameter("height");
		String weight = req.getParameter("weight");
		
		
		// 執行回傳
		try {
			double BMI = calBMI(Double.parseDouble(height), Double.parseDouble(weight));
			String depend = BMIDeterm(BMI);
			resp.getWriter().print(String.format("身高: %sm, 體重: %skg, BMI: %.2f, 是否正常？:%s", height, weight, BMI, depend));
			
		}catch(RuntimeException re) {
			resp.getWriter().print(re.getMessage());
		}
		
		
	}// end of doGet
	
	
	
	// 設計 BMI calculator:
	private double calBMI(double hi, double wei) {
		try {
			return wei/((hi/100) * (hi/100));
			
		}catch(RuntimeException re) {
			System.out.println(re.getMessage());
			return 0;
		}
	}
	
	// 設計 BMI 判斷器:
	private String BMIDeterm(double bmi) {
		try {
			if(bmi > 0 && bmi <= 18) {return "過輕";}
			else if(bmi > 24) {return "過重";}
			else {return "正常";}
		
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	
}// end of Servlet
