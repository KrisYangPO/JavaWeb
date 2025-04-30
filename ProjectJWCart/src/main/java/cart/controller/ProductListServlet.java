package cart.controller;

import java.io.IOException;
import java.util.List;

import cart.service.impl.ProductServiceImpl;
import cart.model.dto.ProductDTO;
import cart.service.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/list")
public class ProductListServlet extends HttpServlet {
	
	// 建立 ProductService 物件
	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 建立 ProductDTOs
		List<ProductDTO> productDTOs = productService.findAllProducts();
		req.setAttribute("productDTOs", productDTOs);
		// 取得所有商品
		req.getRequestDispatcher("/WEB-INF/view/cart/product_list.jsp").forward(req, resp);
		
	}
}
