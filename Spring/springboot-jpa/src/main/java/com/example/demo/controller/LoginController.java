package com.example.demo.controller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.exception.CertException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.service.CertService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController {

    private final ModelMapper modelMapper;
	
	@Autowired
	private CertService certService;

    LoginController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
	
	@GetMapping
	public String loginPage() {
		return "login";
	}
	
	@PostMapping
	public String checkLogin(@RequestParam String username, @RequestParam String password, 
			Model model, HttpSession session) throws CertException {
		// 得到憑證
		UserCert userCert = null;
//		try {
			userCert = certService.getCert(username, password);
			
//		}catch (CertException e) {
//			session.invalidate(); // 發生錯誤時，將殘留在 session 中資訊刪除。
//			model.addAttribute("message", e.getMessage());
//			e.printStackTrace();
//			return "err";
//		}
		
		// 將憑證放到 session 變數中，以利其他程式進行取用與驗證。
		session.setAttribute("userCert", userCert);
		return "redirect:/room"; // 重導向首頁。
	}
	
	@ExceptionHandler(CertException.class)
	public String getCertException(CertException e, Model model, HttpSession session) {
		String type = e.getClass().getSimpleName();
		String message = e.getMessage();
		
		session.invalidate();
		model.addAttribute("message", e.getMessage());
//		e.printStackTrace();
		return "error";
	}
}
