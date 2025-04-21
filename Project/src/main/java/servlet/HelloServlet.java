package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*@WebServlet 表示瀏覽器可以透過 http://localhost:8080/JavaWeb/hello 網址執行到此 Servlet 程式。
 http://localhost:8080/JavaWeb/ 固定的。"/hello" 是 @WebServlet(urlPatterns =sssssw) 當中設定。
 可以決定網頁名字，如果有兩個名字就代表有兩個網址，可能會針對兩個以上的網址進行邏輯運算，或是呈現一樣的東西。
 (名稱一定要先以"/" 開頭，不然程式會出錯)*/
//@WebServlet(urlPatterns = "/hello")

// 給予多個網址名稱，對這些網址做相同的商業邏輯運算。
@WebServlet(urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {

	@Override
	// 表單的請求為 doPost, 網址的請求為 doGet
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 將字串回應給瀏覽器。
		resp.getWriter().print("Hello JavaServlet.");
	
	}
}// end of Servlet
