package cart.controller;

import java.io.IOException;
import java.util.List;

import cart.service.OrderService;
import cart.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cart.model.dto.*;

// 刪除購物車項目
@WebServlet("/product/cart/item/delete")
public class CartItemDeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 取得 index
		int index = Integer.parseInt(req.getParameter("id"));
		
		// 修改紀錄在 session 當中的 cart 
		HttpSession session = req.getSession();
		if(session.getAttribute("cart") != null) {
			
			List<OrderDTO> carts = (List<OrderDTO>)session.getAttribute("cart");
			carts.remove(index);
			// 回傳給 session 覆蓋原本的 cart，但其實不用 (session 是 reference type)
			session.setAttribute("carts", carts);
		}
		resp.sendRedirect("/JavaWebCart/product/cart");
	}
}
