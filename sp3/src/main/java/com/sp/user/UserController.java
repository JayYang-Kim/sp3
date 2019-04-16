package com.sp.user;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.common.FileManager;

@Controller("user.userController")
public class UserController {
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/user/test")
	public String user() throws Exception {
		return "user/test";
	}
	
	@RequestMapping(value="/user/main")
	public String main(String age,
			Model model) throws Exception {
		int a = Integer.parseInt(age);
		String result = "성인";
		
		if(a < 19) {
			result = "미성년자";
		}
		
		model.addAttribute("result", result);
		
		return "user/main";
	}
	
	@RequestMapping(value="/user/main2")
	public String main2(@RequestParam int age,
			Model model) throws Exception {
		String result = "성인";
		
		if(age < 19) {
			result = "미성년자";
		}
		
		model.addAttribute("result", result);
		
		return "user/main";
	}
	
	@RequestMapping(value="/user/request", method = RequestMethod.GET)
	public String requestForm() throws Exception {
		return "user/write";
	}
	
	@RequestMapping(value="/user/request", method = RequestMethod.POST)
	public String requestSubmit(User dto,
			Model model) throws Exception {
		String pathname = "c:" + File.separator + "temp" + File.separator + "user";
		/*String pathname = "c:/temp/user";*/
		
		String result;
		if(dto.getSelectFile() != null) {
			String saveFilename = fileManager.doFileUpload(dto.getSelectFile(), pathname);
			String originalFilename = dto.getSelectFile().getOriginalFilename();
			
			result = "저장 파일명 : " + saveFilename + "<br/>";
			result += "원래 파일명 : " + originalFilename;
		} else {
			result = "업로드된 파일이 없습니다.";
		}
		
		model.addAttribute("msg", result);
		
		return "user/result";
	}
}
