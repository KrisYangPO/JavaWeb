package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.*;
import model.*;


@WebServlet(urlPatterns = "/CoffeeOrder")
public class CoffeeOrderServlet extends HttpServlet {
		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 設定編碼，對 Servlet 跟瀏覽器都要同時設定。
		// 對 Servlet 設定編碼
		resp.setCharacterEncoding("UTF-8");
		// 對瀏覽器通知編碼：
		resp.setContentType("text/html; charset=UTF-8");
		
		// 取得資訊
		String item = req.getParameter("item");
		String size = req.getParameter("size");
		String sugar = req.getParameter("sugar");
		
		// 判斷式：
		if(item == null || size == null || sugar == null) {
			resp.getWriter().print("參數輸入不正確");
			return; // 方法直接被停止
		}
		// 執行商業邏輯：
		//localhost:8080/JavaWeb/CoffeeOrder?item=Americano&size=M&sugar=false
		CoffeeOrder coffeeorder = new CoffeeOrder(item, size, sugar);		
		// resp.getWriter().print(coffeeorder.getInfor());
		
		// 建立分派器：
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/coffee_order.jsp");
		req.setAttribute("coffeeOrder", coffeeorder);
		rd.forward(req, resp);
	}

} // end of CoffeeOrderServlet

