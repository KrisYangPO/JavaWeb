package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Utils;

@WebServlet(urlPatterns = "/welcome")
public class Welcome extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 設定編碼，對 Servlet 跟瀏覽器都要同時設定。
		// 對 Servlet 設定編碼
		resp.setCharacterEncoding("UTF-8");
		// 對瀏覽器通知編碼：
		resp.setContentType("text/html; charset=UTF-8");
		
		
//		// 接收參數：
//		// http://localhost:8080/JavaWeb/welcome?name=Jack
//		// 用? 分隔網址跟帶入參數：name=Jack
//		String name = req.getParameter("name");
//		resp.getWriter().print(name + "歡迎光臨");
		
		
		// 如果要新增兩個以上的參數，要用 "&" 分隔每個參數。
		// http://localhost:8080/JavaWeb/welcome?name=Jack&age=19
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		
		// 用 isNumber 判斷 age 是不是數值字串，如果是就可以執行年齡判斷，否則不用。
		if (Utils.isNumber(age)) {
			String message = "未成年";
			if(Integer.parseInt(age) >= 18) {message = "成年";}
			resp.getWriter().print(name + "歡迎光臨" + "年齡為：" + age + "成年與否？" + message);
			
		}else {
			resp.getWriter().print(name + "歡迎光臨");
		}

		
	}// end of doGet()
	
	


	
	
}// end of Welcome
