package com.sp.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("demo.demoController")
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping(value="/demo/request", method = RequestMethod.GET)
	public String form() throws Exception {
		return "demo/main";
	}
	
	@RequestMapping(value="/demo/request", method = RequestMethod.POST)
	public String submit(@RequestParam Map<String, Object> paramMap,
			Model model) throws Exception {
		
		String msg = "추가 성공";
		
		try {
			demoService.insertDemo(paramMap);
		} catch (Exception e) {
			msg = "추가 실패";
		}
		
		model.addAttribute("msg", msg);
		
		return "demo/result";
	}
}
