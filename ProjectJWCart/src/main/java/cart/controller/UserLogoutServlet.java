package cart.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/logout")
public class UserLogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 將 session 裡所存放的資訊都失效，所以 UserLoginServlet 所儲存的 userDTO 就會直接失效。
		HttpSession session = req.getSession();
		session.invalidate();
		
		// 點選會員登出的時候，一樣會重新回到 userLogin 的畫面，可以重新登入。
		req.getRequestDispatcher("/WEB-INF/view/cart/user_login.jsp").forward(req, resp);
	}
	
}
