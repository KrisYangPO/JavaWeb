package cart.controller;

import java.io.IOException;
import java.util.List;

import cart.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cart.model.dto.UserDTO;
import cart.service.*;
import cart.model.dto.*;

// 購物車結帳
@WebServlet("/product/cart/submit")
public class CartSubmitServlet extends HttpServlet {
	
	private OrderService orderService = new OrderServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 從 session 中搜尋 userId 與 cart 資訊。
		HttpSession session = req.getSession();
		// 取得當前登入使用者 DTO 物件
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		// 取得當前購物車 "carts" 所儲存的產品DTO集合(你下訂的哪些產品)
		List<ProductDTO> carts = (List<ProductDTO>)session.getAttribute("cart");
		
		// 新增訂單到資料表(addOrder + addOrderItem)
		orderService.addOrder(userDTO.getId(), carts);
		
		// 清空購物車
		session.removeAttribute("cart");
		
		// 重導到 result.jsp
		req.setAttribute("resultTitle", "購物車結帳");
		req.setAttribute("resultMessage", "購物車結帳完畢");
		req.getRequestDispatcher("/WEB-INF/view/cart/result.jsp").forward(req, resp);
				
	}
}
