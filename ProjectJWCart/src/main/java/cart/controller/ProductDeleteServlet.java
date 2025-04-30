package cart.controller;

import java.io.IOException;
import java.util.List;

import cart.model.dto.ProductDTO;
import cart.service.ProductService;
import cart.service.impl.ProductServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//在 product/list.jsp 牽動 delete 時可以執行的 doGet
@WebServlet("/product/delete")
public class ProductDeleteServlet extends HttpServlet {

	private ProductService productService = new ProductServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 在觸發 delete 的網址中，夾帶了一個 id=...的參數：
		// <a href = "/JavaWebCart/product/delete?id=${productDTO.productId}"
		// id 就是欄位的 index，也是 product 在資料庫記錄的 id。
		
		String id = req.getParameter("id");
		// 執行刪除
		productService.delete(Integer.parseInt(id));
		
		// 重跑商品管理頁面 list。
		resp.sendRedirect("/JavaWebCart/product/list");
	}
}
