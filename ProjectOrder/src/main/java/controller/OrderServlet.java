package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.OrderDTO;
import service.OrderService;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	
	// Service 要負責呼叫 DAO
	private OrderService orderservice = new OrderService();
	
	// 查看歷史資料
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得歷史資料
		List<OrderDTO> orderDTOs = orderservice.getOrderHistory();
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/history.jsp");
		// 將歷史資料導入 history.jsp 裡。
		req.setAttribute("orderDTOs", orderDTOs);
		rd.forward(req, resp);
	}
	
	// 接收訂單的請求
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		// 使用者在表單選取的商品 (index.jsp)
		String item =req.getParameter("item");
		// 新增訂單，並且得到反饋
		OrderDTO orderDTO = orderservice.addOrder(item);
		
		// 將反饋 (OrderDTO 回傳 message) 導入 result.jsp 裡。
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
		req.setAttribute("orderDTO", orderDTO);
		rd.forward(req, resp);
		
	}
	
}
