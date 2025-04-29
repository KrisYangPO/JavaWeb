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
		String id = req.getParameter("id");
		// 執行刪除
		productService.delete(Integer.parseInt(id));
		
		// 重新導向 list。
		resp.sendRedirect("/JavaWebCart/product/list");
	}
}
