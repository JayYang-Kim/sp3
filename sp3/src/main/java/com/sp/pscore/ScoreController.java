package com.sp.pscore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.common.MyUtil;

@Controller("pscore.scoreController")
public class ScoreController {
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="/pscore/list")
	public String list(@RequestParam(value="page", defaultValue="1") int current_page,
			Model model) throws Exception {
		
		int total_page;
		
		return "pscore/list";
	}
	
	@RequestMapping(value="/pscore/write", method = RequestMethod.GET)
	public String writeForm(Model model) throws Exception {
		
		model.addAttribute("mode", "insert");
		
		return "pscore/write";
	}
	
	@RequestMapping(value="/pscore/write", method = RequestMethod.POST)
	public String writeSubmit(Score dto, Model model) throws Exception {
		
		int result = scoreService.insertScore(dto);
		
		if(result == 0) {
			model.addAttribute("mode", "insert");
			model.addAttribute("mode", "데이터 등록을 실패했습니다.");
			return "/pscore/write";
		}
		
		return "redirect:/pscore/list";
	}
}
