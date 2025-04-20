package Practice1.servlet;

import java.io.IOException;

import Practice1.model.DrinkOrder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DrinkOrder")
public class DrinkOrderServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 設定編碼，對 Servlet 跟瀏覽器都要同時設定。
		// 對 Servlet 設定編碼
		resp.setCharacterEncoding("UTF-8");
		// 對瀏覽器通知編碼：
		resp.setContentType("text/html; charset=UTF-8");
		
		// 取得資訊
		String item = req.getParameter("type");
		String size = req.getParameter("size");
		String ice = req.getParameter("ice");
		
		// 判斷式：
		if(item == null || size == null || ice == null) {
			resp.getWriter().print("參數輸入不正確");
			return; // 方法直接被停止
		}
		
		// 帶入商業邏輯的物件實體
		DrinkOrder DO = new DrinkOrder(item, size, ice);
		
		// 建立分派器：
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/drink_order.jsp");
		
		// 將整個 DrinkOrder 物件傳給 JSP
		req.setAttribute("drinkorder", DO);
		rd.forward(req, resp);
		
	}
	

	
}// end of DrinkOrderServlet

// http://localhost:8080/JWpractice/DrinkOrder?type=milkTea&size=L&ice=yes

