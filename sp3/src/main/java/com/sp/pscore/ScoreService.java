package com.sp.pscore;

import java.util.List;
import java.util.Map;

public interface ScoreService {
	public int insertScore(Score dto);
	public int updateScore(Score dto);
	public int deleteScore(String hak);
	public int dataCount();
	public Score readScore(String hak);
	public List<Score> listScore(Map<String, Object> map);
}
