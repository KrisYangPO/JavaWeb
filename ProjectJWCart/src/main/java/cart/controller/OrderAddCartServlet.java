package cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cart.service.ProductService;
import cart.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cart.model.dto.*;

// 將商品加入購物車裡，進行暫存。
// 將所訂購商品，暫時存放在 "Session" 當中，而並不是將訂單紀錄放在資料庫。

@WebServlet("/product/order/add/cart")
public class OrderAddCartServlet extends HttpServlet {
	
	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		// 建立準備存放產品的購物車，
		// 但是要先確認購物車是否有之前欲購買的商品：
		List<ProductDTO> cart = null; 
		
		// 確認 Session 是否已經存在有購物車資訊：
		if(session.getAttribute("cart") == null) {
			cart = new ArrayList<>();
		}else {
			// 目前在使用中的購物車(之前已經加入的購物車)
			// 需要將 session 物件裡的 "cart" 轉型成 List<ProductDTO> 才會是集合。
			cart = (List<ProductDTO>)session.getAttribute("cart"); 
		}
		
		// 取得購買商品 id：
		// 點選購物車時，會取得 ProductDTO 的 productId:
		// href = "/JavaWebCart/product/order/add/cart?productId=${ productDTO.productId }"
		Integer productId = Integer.parseInt(req.getParameter("productId"));
		// 根據 id 取得 ProductDTO:
		Optional<ProductDTO> optProductDTO = productService.findAllProducts()
				.stream()
				.filter(dto -> dto.getProductId().equals(productId)) 
				.findFirst();
		
		// filter() 是確認資料庫有紀錄這個 Product，
		// 一定會有因為 ProductId 也是根據 ProductDTOs 集合所取得的資訊，
		// 是由 OrderServlet @WebServlet("/product/order") 也是執行 productService.findAllProducts()，
		// 取得目前資料庫紀錄的登入產品。
		
		
		// 確認有 productDTO，將這個物件加入 session 當中的 cart List<productDTO> 集合。
		// 就可以讓所有 JSP 網頁知道 cart 裡面有哪些 ProductDTO。
		if(optProductDTO.isPresent()) {
			// 於購物車中加入一筆商品。
			cart.add(optProductDTO.get());
			// 新增完後，再次將 cart 帶入 session。
			session.setAttribute("cart", cart);
		}
		// 回到訂單主頁
		resp.sendRedirect("/JavaWebCart/product/order");
		// 接下來就要顯示購物車的頁面。
		
		// 列印 session 裡面的 cart:
//		System.out.println(session.getAttribute("cart"));
	}
}

// 所以結束後，Session 就會儲存你點選的產品，將他的 ProductDTO 儲存起來。
