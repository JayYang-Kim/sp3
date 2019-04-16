package com.sp.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sp.error.MyRuntimeException;

//@RestController : ajax 전용 컨트롤러
//@RestController는 @ResponseBody 어노테이션을 붙이지 않아도 amp이 json으로 변환된다.
@RestController("user.testController")
public class TestController {
	@RequestMapping(value="/test/main")
	public Map<String, Object> execute(@RequestParam int age) {
		Map<String, Object> model = new HashMap<>();
		
		if(age < 0 || age > 120) {
			throw new MyRuntimeException("나이는 0 ~ 120사이만 입력 가능합니다.");
		}
		
		String s = "성인";
		if(age < 19) {
			s = "미성년자";
		}
		
		model.put("age", age);
		model.put("result", s);
		
		return model;
	}
}
