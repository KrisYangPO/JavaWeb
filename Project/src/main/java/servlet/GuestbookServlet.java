package servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Guestbook;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet{
	
	// 因為使用 static 所以每個物件都可以使用它，
	// 另外，因為每個人都可以使用，所以每次使用它，他的值就會一直被改變。
	private static final List<Guestbook> guestbooks = new CopyOnWriteArrayList<>();
	// CopyOnWriteArrayList 允許多人存取寫入的陣列
	// 很多人進行存取就會有誤差問題 -> Thread safe
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 可以將 forward 串接在後面。
		req.getRequestDispatcher("/WEB-INF/guestbook_form.jps").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mesg = req.getParameter("message");
		// 建立物件 
		Guestbook guestbook = new Guestbook(mesg);
		guestbooks.add(guestbook);
		// 導入result.jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/guestbook_result.jps");
		req.setAttribute("message", mesg); //本次留言(本次物件)
		req.setAttribute("guestbook", guestbooks); //搜集陣列的內容。
		rd.forward(req, resp);
	}
	
	
}
