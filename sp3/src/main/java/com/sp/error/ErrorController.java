package com.sp.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("error.errorController")
public class ErrorController {
	@RequestMapping(value="/error/error404")
	public String error404() {
		return ".error.error404";
	}
}
