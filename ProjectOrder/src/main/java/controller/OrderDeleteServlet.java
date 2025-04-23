package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.OrderDTO;
import service.OrderService;

// 在 history.jsp 牽動 delete 時可以執行的 doGet
@WebServlet("/order/delete")
public class OrderDeleteServlet extends HttpServlet {
	// 使用 Service 與 Servlet 連接
	private OrderService orderservice = new OrderService(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String index = req.getParameter("index");
		// 執行刪除
		OrderDTO orderDTO = orderservice.removeOrder(index);
		
		// 將反饋 (OrderDTO 回傳 message) 導入 result.jsp 裡。
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
		req.setAttribute("orderDTO", orderDTO);
		rd.forward(req, resp); 
	}
	
}
