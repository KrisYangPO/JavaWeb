package cart.controller;

import java.io.IOException;

import cart.service.impl.UserLoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cart.model.dto.UserDTO;
import cart.service.*;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
	
	private UserLoginService userLoginService = new UserLoginServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/cart/user_login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String authcode = req.getParameter("authcode");
		// 目前的驗證碼 (加上一個空字串""，結果就會是字串結果)：
		String sessionAuthcode = session.getAttribute("authcode")+"";
		
		// 建立 result 回報字串
		String resultMessage = null;
		
		try {
			// 建立判斷結果，沒有建立成功就會拋出 Exception，
			UserDTO userDTO = userLoginService.login(username, password, authcode, sessionAuthcode);
			resultMessage = username + " 登入成功！";
			
			// 將登入資訊存入 session 中：
			// 存入 session 就可以讓所有在這個 Server 上的 JSP 都可以知道 "userDTO" 就是 userDTO 物件。
			session.setAttribute("userDTO", userDTO);
			
			/* Session:
			 * 一般將 Servlet 變數交給 JSP 就是 setAttritbute，
			 * 在 JSP 就會執行 ${"變數"} 或是 getAttribute("變數")，在 JSP 儲存內容。
			 * 而 session.setAttribute 則是將變數儲存在 Server 當中，使所有相同瀏覽器 id 的 JSP 都可以使用這個變數。
			 * 所以每當有變數需要交給所有 JSP 使用，就呼叫 session 物件，用 session.setAttribute 儲存。
			 * 在 JSP 就可以用 ${sessionScope.變數名稱} 呼叫內容 (或是 ${變數名稱})。
			 */
			
			
		}catch(RuntimeException e) {
			// 移除舊有的登入資訊：
			session.removeAttribute("userDTO");
			resultMessage = e.getMessage();
		}
		
		// 要給 result.jsp 資訊
		req.setAttribute("resultTitle", "登入結果");
		req.setAttribute("resultMessage", resultMessage);
		req.getRequestDispatcher("/WEB-INF/view/cart/result.jsp").forward(req, resp);
	}
	
	
}
