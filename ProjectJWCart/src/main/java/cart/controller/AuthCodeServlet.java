package cart.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 產生認證碼的 Servlet
@WebServlet("/user/authcode")
public class AuthCodeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Random random = new Random();
		String authcode = String.format("%04d", random.nextInt(10000)); // 0000~9999 的隨機數
		ImageIO.write(getAuthCodeImage(authcode), "JPEG", resp.getOutputStream());
	}
	
	// 利用 Java2D 產生動態圖像
	private BufferedImage getAuthCodeImage(String authcode) {
		// 建立圖像區域(80x30 RGB)
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_BGR);
		// 建立畫布：
		Graphics g = img.getGraphics();
		// 設定顏色
		g.setColor(Color.YELLOW);
		// 塗滿背景，全區域塗滿
		g.fillRect(0, 0, 80, 30);
		// 設定顏色
		g.setColor(Color.black);
		// 設定字型 Font(字體，風格，大小)
		g.setFont(new Font("Arial", Font.BOLD, 22));
		// 繪文字 (18~22) 表示繪文字的左上角起點。
		g.drawString(authcode, 18, 22); 
	
		return img;
	}
}
