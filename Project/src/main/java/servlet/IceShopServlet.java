package servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

@WebServlet("/iceshop")
public class IceShopServlet extends HttpServlet{
	private static final List<IceShop> iceOrders = new CopyOnWriteArrayList<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/iceshop_form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 從 form.jsp 帶入參數，交給 Java model 物件建構子。
		String main = req.getParameter("mainDish");
		// 如果是多選，就要用 req.getParameterValues 將多選參數帶入，儲存在一個 String[] 陣列中。
		String[] dressing = req.getParameterValues("topping");
		
		// 物件實體 Java model
		IceShop isp = new IceShop(main, dressing);
		// 將點餐紀錄儲存起來
		iceOrders.add(isp);
		
		// 分派器指向 JSP
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/iceshop_result.jsp");
		// 帶入參數 iceshop 儲存 isp 物件實體，帶入 JSP
		// 將點餐紀錄也送出去給 result.JSP
		req.setAttribute("iceshop", isp); 
		req.setAttribute("iceorderlist", iceOrders); 
		rd.forward(req, resp);
		
	}
	
}
