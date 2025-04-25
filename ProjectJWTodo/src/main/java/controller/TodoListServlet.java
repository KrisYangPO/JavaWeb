package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.TodoDTO;
import service.*;

@WebServlet("/todolist/*")
public class TodoListServlet extends HttpServlet {
	
	private TodoListService service = new TodoListServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// @WebServlet("/todolist/*") 讓網址後面可以輸入任意網址名稱，
		// 因此在這裡可以設計根據什麼名稱要執行什麼：
		String pathInfo = req.getPathInfo();
//		resp.getWriter().print("pathInfo =" + pathInfo);
		
		// 帶入參數
		String id = req.getParameter("id");
		String completed = req.getParameter("checked");
		String text = req.getParameter("text");
		
		// 根據不同需求，讓 JSP 指派不同功能來呼叫 Servlet 的 Service 控制。
		switch(pathInfo) {
			// 顯示 / 或是 /* 都會有首頁
			case "/":
			case "/*":
				// 顯示 Todo List 首頁
				List<TodoDTO> todos = service.findAllTodos();
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/todolist.jsp");
				req.setAttribute("todos", todos);
				rd.forward(req, resp);
				return;
				
			case "/update":				
				if(completed != null) {
					// 修改 Todo 完成狀態
					service.updateTodoComplete(Integer.parseInt(id), Boolean.parseBoolean(completed));
					// 更新完後，重跑回首頁
					resp.sendRedirect("/JavaWebToDo/todolist/");
					return;
				} else if(text != null) {
					// 修改內容。
					service.updateTodoText(Integer.parseInt(id), text);
					// 更新完後，重跑回首頁
					resp.sendRedirect("/JavaWebToDo/todolist/");
					return;
				}
				break;
								
			case "/delete":
				service.deleteTodo(Integer.parseInt(id));
				// 刪除完後，重跑回首頁
				resp.sendRedirect("/JavaWebToDo/todolist/");
				return;
				
			default:
				return;
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
		
		// 根據表單輸入 text 內容，就新增到資料庫
		String text = req.getParameter("text");
		Boolean completed = false;
		service.addTodo(text, completed);
		
		// 重跑首頁，因為剛新增，就要將新增的東西在首頁顯示
		resp.sendRedirect("/JavaWebToDo/todolist/");
		
	}
	
	// doGet 與 doPOST 差異
	// doGet 將參數帶入 Servlet 時，會將參數顯示在 URL 後面：？paramter=value，
	// doPOST 不會顯示參數內容到 URL 上，會是 payLoad 的形式傳遞。
	// 所以少量，且不敏感的資訊就可以用 doGet，資訊大量且敏感就會用 Post 且需要用 form 標籤。
	// 網頁不會支援用 hyperlink <a method = "post"> 這種格式，所以 Post 通常會在 <form method="POST"> 所使用
	
}
