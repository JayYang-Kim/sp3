package com.sp.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("member.loginController")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/member/login" ,method = RequestMethod.GET)
	public String loginForm() throws Exception {
		return ".member.login";
	}
	
	@RequestMapping(value="/member/login", method = RequestMethod.POST)
	public String loginSubmit(@RequestParam String userId,
			@RequestParam String userPwd,
			Model model) throws Exception {
		
		boolean b= loginService.requestLogin(userId, userPwd);
		
		if(!b) {
			model.addAttribute("msg", "아아디 또는 패스워드가 일치하지 않습니다.");
			return ".member.login";
		}
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/member/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	
}
