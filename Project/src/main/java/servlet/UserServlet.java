package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 建立分派器：
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/user_form.jsp");
		rd.forward(req, resp);
		
		
	}// end of doGet

	
	
	// 處理表單
	// doGet 傳送需求給 JSP，JSP<body> 的 <form method = "POST"> 表示執行完 doGet 後續，
	// 就會執行這裡的 doPost，action 告知這個位置在："http://localhost:8080/JavaWeb/user".
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收表單資料
		// 請求編碼：使用者輸入表單資訊，可能得到中文訊息，此時就需要處理編碼設定：
		req.setCharacterEncoding("UTF-8");
		
		// 設定編碼，對 Servlet 跟瀏覽器都要同時設定。
		// 對 Servlet 設定編碼
		resp.setCharacterEncoding("UTF-8");
		// 對瀏覽器通知編碼：
		resp.setContentType("text/html; charset=UTF-8");
		
		// 接受參數，接收參數的來源就是 user_form.Jsp 由網頁表單帶入的結果
		// 所以這粒的 getParameter 參數要與 JSP 設定的參數名稱一致：
		String userName = req.getParameter("userName"); // userName 要與 user_form.jsp 設定一致。
		String gender = req.getParameter("gender"); // gender 要與 user_form.jsp 一致
		String age = req.getParameter("age"); // age 要與 user_form.jsp 一致
		String height = req.getParameter("height"); // height 要與 user_form.jsp 一致
		String weight = req.getParameter("weight"); // weight 要與 user_form.jsp 一致
		
		// 將參數交給 Model (User.java)
		User u = new User(userName, gender, age, weight, height);
		resp.getWriter().print(u);
		
		// 分配 POST 到 result JSP:
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/user_result.jsp");
		req.setAttribute("u", u);
		rd.forward(req, resp);
	}
	
	
	
}// end of UserServlet
