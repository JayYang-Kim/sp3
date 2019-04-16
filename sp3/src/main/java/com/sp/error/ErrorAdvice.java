package com.sp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/*
 * - @ContollerAdvice (예외를 catch해서 처리)
 * @Controller, @RestContoller(ajax 전용 컨트롤러 / @responseBody 어노테이션을 안붙여도 된다.)에서 발생한 예외를 감지하고 적절한 응답을 만들어 낼때 사용
 */

@ControllerAdvice
public class ErrorAdvice {
	//@RestController 예외처리
	@ExceptionHandler(value= {MyRuntimeException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public MyErrorMessage handleException(RuntimeException e, WebRequest request) {
		MyErrorMessage msg = new MyErrorMessage();
		
		msg.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString()); // 500
		msg.setMessage(e.getMessage());
		
		return msg;
	}
	
	//@Controller 예외처리
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView mav = new ModelAndView(".error.error");
		mav.addObject("errorMsg", e.getMessage());
		return mav;
	}
}
