package com.sp.score;

import java.util.List;
import java.util.Map;

public interface ScoreService {
	public void insertScore(Score dto) throws Exception;
	public int dataCount() throws Exception;
	public List<Score> listScore(Map<String, Object> map) throws Exception;
	public Score readScore(String hak) throws Exception;
	public void updateScore(Score dto) throws Exception;
	public void deleteScore(String hak) throws Exception;
}
