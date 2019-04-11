package com.sp.score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.common.MyUtil;

@Controller("score.scoreController")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreSerivce;
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="/score/main")
	public String main() throws Exception {
		return "score/main";
	}
	
	@RequestMapping(value="/score/list")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(value="pageNo", defaultValue="1") int current_page) throws Exception {
		Map<String, Object> model = new HashMap<>();
		
		int dataCount = 0;
		int total_page = 0;
		int rows = 10;
		
		dataCount = scoreSerivce.dataCount();
		if(dataCount != 0) {
			total_page = myUtil.pageCount(rows, dataCount);
		}
		
		if(current_page > total_page) {
			current_page = total_page;
		}
		
		int start = (current_page - 1) * rows + 1;
		int end = current_page * rows;
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		List<Score> list = scoreSerivce.listScore(map);
		
		String paging = myUtil.pagingMethod(current_page, total_page, "listPage");
		
		model.put("list", list);
		model.put("dataCount", dataCount);
		model.put("pageNo", current_page);
		model.put("total_page", total_page);
		model.put("paging", paging);
		
		return model;
	}
	
	@RequestMapping(value="/score/insert", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insert(Score dto) throws Exception {
		Map<String, Object> model = new HashMap<>();
		
		try {
			scoreSerivce.insertScore(dto);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		
		return model;
	}
	
	@RequestMapping(value="/score/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(Score dto) throws Exception {
		Map<String, Object> model = new HashMap<>();
		
		try {
			scoreSerivce.updateScore(dto);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		
		return model;
	}
	
	@RequestMapping(value="/score/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam String hak) throws Exception {
		Map<String, Object> model = new HashMap<>();
		
		try {
			scoreSerivce.deleteScore(hak);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		
		return model;
	}
}
