package com.sp.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sp.member.LoginService;

@Controller("bbs.boardController")
public class BoardController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/bbs/list")
	public String list() {
		if(loginService.loginMemberInfo() == null) {
			return ".member.login";
		}
		
		return ".bbs.list";
	}
}
