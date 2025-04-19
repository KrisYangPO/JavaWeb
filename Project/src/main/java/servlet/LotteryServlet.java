package servlet;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns ="/lottery")
public class LotteryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Random random = new Random();
		Set<Integer> numList = new LinkedHashSet<Integer>();
		
		// 一直將隨機數帶入 Set 集合值到長度大於 5
		// 因為 Set 不會儲存重複值，所以一直帶入不會遇到重複值問題
		while(numList.size() < 5) {
			int num = random.nextInt(39) + 1; 
			numList.add(num);
		}
		// resp 呈現至瀏覽器：
//		resp.getWriter().print(numList);
		
		// MVC model2：
		// 利用調度器，將 Servlet 結果交給 JSP 呈現給前端瀏覽器，
		// 其實 JSP 具有 http 語法，可以直接渲染網頁結果，屬於前後端結合的架構。
		// 建立調度器，決定要將 Servlet 結果傳給 "哪一個" JSP 渲染器。 
		RequestDispatcher rd = req.getRequestDispatcher("/Lottery.jsp");
		
		// 傳遞樂透號碼：
		// setAttribute 設定要傳遞的內容為 numList，用變數 "numbers" 儲存。
		// 就可以在 Lottery.jsp 當中用 ${JSPnumbers} 呼叫變數的儲存內容：numList
		req.setAttribute("JSPnumbers", numList);
		
		// forward 除了要讓 JSP 知道要傳遞什麼 Servlet 的結果：req 物件
		// 也要讓 JSP 要將結果呈現給哪個網址，向誰回應：response。
		rd.forward(req, resp);
		
	}// end of doGet()
	
}// end of Lottery
