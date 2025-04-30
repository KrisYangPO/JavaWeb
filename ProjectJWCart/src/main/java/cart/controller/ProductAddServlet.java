package cart.controller;

import cart.service.impl.ProductServiceImpl;

import java.io.IOException;

import cart.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/add")
public class ProductAddServlet extends HttpServlet {
	
	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/cart/product_list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productName = req.getParameter("productName");
		String price = req.getParameter("price");
		String qty = req.getParameter("qty");
		String imageBase64 = req.getParameter("productImage");
		
		productService.add(productName, price, qty, imageBase64);
		
		resp.sendRedirect("/JavaWebCart/product/list");
	}
	
	
}
