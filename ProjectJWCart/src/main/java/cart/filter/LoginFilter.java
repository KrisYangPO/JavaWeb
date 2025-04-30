package cart.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 要過濾哪些路徑
// 所有網頁路徑上在 product 之下，都需要經過這個 filter，登入才可以通過 filter
@WebFilter(urlPatterns = {"/user/list", "/product/*"})
public class LoginFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 根據 Session 中屬性是否有 userDTO 物件，來判斷是否有登入的使用者
		HttpSession session = request.getSession();
		
		// 如果 userDTO 內容是 null，表示沒有登入。
		if(session.getAttribute("userDTO") == null) {
			
			// 如果沒有登入就想看 list，直接重導到登入頁面
//			response.sendRedirect("/user/login");
			
			// 或是導向 result 頁面，告訴你要先登入才可以執行 "/user/list 和 /product/list"
			request.setAttribute("resultTitle", "請先登入");
			request.setAttribute("resultMessage", "請先登入");
			request.getRequestDispatcher("/WEB-INF/view/cart/result.jsp").forward(request, response);
			
		}else {
			// By pass
			// 如果有登入成功，直接跳過這個 filter 往下執行，前往 /user/list 和 /product/list
			chain.doFilter(request, response);
		}
		
	}
	
}
