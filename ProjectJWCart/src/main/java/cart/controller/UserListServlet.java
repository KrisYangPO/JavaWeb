package cart.controller;

import java.io.IOException;
import java.util.List;

import cart.model.dto.UserDTO;
import cart.service.impl.UserListService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/list")
public class UserListServlet extends HttpServlet {

	private  UserListService service = new UserListService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 顯示所有內容。
		List<UserDTO> users = service.findAllUsers();
		req.setAttribute("users", users);
		//導向頁面
		req.getRequestDispatcher("/WEB-INF/view/cart/user_list.jsp").forward(req, resp);
	}

	
}
