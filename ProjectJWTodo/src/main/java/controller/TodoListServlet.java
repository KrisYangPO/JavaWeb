package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/todolist/*")
public class TodoListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// @WebServlet("/todolist/*") 讓網址後面可以輸入任意網址名稱，
		// 因此在這裡可以設計根據什麼名稱要執行什麼：
		String pathInfo = req.getPathInfo();
//		resp.getWriter().print("pathInfo =" + pathInfo);
		
		switch(pathInfo) {
			case "/":
				// 顯示 Todo List 首頁
				break;
				
			case "/update":
				String id = req.getParameter("id");
				String completed = req.getParameter("checked");
				String text = req.getParameter("text");
				
				if(completed != null) {
					// 修改 Todo 完成狀態
				}else if(text != null) {
					// 修改內容。
				}
				
				break;
				
			case "/delete":
				break;
				
			default:
				break;
		}
		// add 會是 doPost 執行
		
	}// end of doGet

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// @WebServlet("/todolist/*") 讓網址後面可以輸入任意網址名稱，
		// 因此在這裡可以設計根據什麼名稱要執行什麼：
		String pathInfo = req.getPathInfo();
		
		if(! pathInfo.equals("/add")){
			// 錯誤網頁路徑
			// 這裡要重蹈新頁面到錯誤路徑位置。
			resp.getWriter().print("Path Error. ");
			return;
		}
		
	}
	
	
	
}
