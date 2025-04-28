package cart.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cart.service.*;
import cart.service.impl.*;

// 要接收使用者於信件中，按下的確認連結
// http://localhost:8080/JavaWebCart/email/confirm?username=Kris

@WebServlet("/email/confirm")
public class EmailConfirmServlet extends HttpServlet {
	
	private UserRegisterService registerService = new UserRegisterServiceImpl();

	
	// 在 UserRegisterServlet 當中，在 getPost 建立使用者時，
	// getPost 會執行 EmailService 功能，發送一段網址給 email (String ConfirmLink = "http://localhost:8080/JavaWebCart/email/confirm?username=" + username;)
	// 所以 email 就會收到這封信，點進去之後，就會指引到這裏 (/JavaWeb/email/confirm Servlet)，
	// 而會用 doGet 接收請求，結果就會執行 registerService.emailConfirmOK() 方法，將 username 認證欄位：completed 設定為 true。
	// 接著會傳送訊息給 result.JSP，呈現認證成功的網頁給使用者看
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得 username 
		String username = req.getParameter("username");
		// 驗證 email，
		registerService.emailConfirmOK(username);
		
		// 準備給 result.jsp 的資訊：
		String resultTitle = "Email 驗證結果";
		String resultMessage = "用戶名稱："+ username + "<p/> Email 驗證成功";
		req.setAttribute("resultTitle", resultTitle);
		req.setAttribute("resultMessage", resultMessage);
		
		// 重導到 result.jsp
		req.getRequestDispatcher("/WEB-INF/view/cart/result.jsp").forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
