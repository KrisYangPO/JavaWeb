package cart.controller;

import java.io.IOException;
import cart.service.impl.*;
import cart.service.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/register")
public class UserRegisterServlet extends HttpServlet {
	
	// 建立新 User
	private UserRegisterService Register_service = new UserRegisterServiceImpl();
	// 發送 email 用
	private EmailService email_service = new EmailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/cart/user_register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//  register 註冊有帳號密碼email
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		// 執行 UserRegisterService 的 addUser
		Register_service.addUser(username, password, email);
		
		// 發送 email
		String ConfirmLink = "http://localhost:8080/JavaWebCart/email/confirm?username=" + username;
		email_service.sendEmail(email, ConfirmLink);
		
		// 準備要給 result.jsp 的資訊
		// 當 email_service.sendEmail 成功寄信出去後，就會將以下訊息傳給 result.jsp
		// 當你在email 中點擊網址，他也會前往 result.jsp
		String resultTitle = "註冊結果";
		String resultMessage = "用戶 " + username + " 註冊成功 !";
		resultMessage += "<p />";
		resultMessage += "系統已送出驗證信件到 " + email + " 信箱, 請收信並點選[確認]連結";
		
		req.setAttribute("resultTitle", resultTitle);
		req.setAttribute("resultMessage", resultMessage);
		
		// 重導到 result.jsp
		req.getRequestDispatcher("/WEB-INF/view/cart/result.jsp").forward(req, resp);
	}
	
	
}
