package cart.controller;

import cart.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.Base64;

import cart.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/product/add")
@MultipartConfig(maxFileSize = 1024*1024*10) // 設定圖片上傳大小 10M
public class ProductAddServlet extends HttpServlet {
	
	// 呼叫 ProductService 執行 CRUD 
	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/cart/product_list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收一般表單資訊
		String productName = req.getParameter("productName");
		String price = req.getParameter("price");
		String qty = req.getParameter("qty");
		
		// 接受檔案表單資訊
//		String imageBase64 = req.getParameter("productImage");
		Part productImage = req.getPart("productImage");
		
		// 將 Part 物件轉 byte[]，再轉字串，並存入到資料表。
		// 將一個物件轉成 byte[] 陣列，可以將物件轉成最原始的資料型態，
		// 再由 byte[] 原始資料型態，轉成其他資料型態(字串)，以便後續儲存。
		byte[] bytes = productImage.getInputStream().readAllBytes();
		// 將 bytes 陣列以 base64 格式轉成字串。
		// 這樣就可以把圖檔當作字串儲存。
		String productImageBase64 = Base64.getEncoder().encodeToString(bytes);
		
		// 儲存至資料庫。
		productService.add(productName, price, qty, productImageBase64);
		
		// 重導到 result.jsp
		String message = String.format("商品名稱：%s</p>商品價格：%s</p>商品庫存：%s</p>商品照片：<img src='data:image/png;base64,%s'><p/>",
										productName, price, qty, productImageBase64);
		
		req.setAttribute("resultTitle", "商品新增成功");
		req.setAttribute("resultMessage", message);
		req.getRequestDispatcher("/WEB-INF/view/cart/result.jsp").forward(req, resp);
		
//		resp.sendRedirect("/JavaWebCart/product/list");
	}
}

// 當檔案在 JSP 上傳時，商品照片：<input type="file" id="productImage"...
// 規定表單標籤：<form> 當中的屬性 enctype 一定要用 "multipart/form-data" 形式。

// 顯示圖檔：
// <img src='data:image/png; base64,%s></p>"
// src 是檔案類型，data為 image/png 用 base64編碼的 "%s"
// 透過在 Servlet 傳輸字串 (String.format) 當中書寫 html 語法，
// 將字串再傳給 JSP，就可以在 JSP 顯示你在 Servlet 書寫的 html。


