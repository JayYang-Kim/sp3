package com.sp.score;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("score.scoreService")
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private CommonDAO dao;
	
	@Override
	public void insertScore(Score dto) throws Exception {
		try {
			dao.insertData("score.insertScore", dto);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;
		}
	}

	@Override
	public int dataCount() throws Exception {
		int result=0;
		try {
			result=dao.selectOne("score.dataCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Score> listScore(Map<String, Object> map) throws Exception {
		List<Score> list=null;
		try {
			list=dao.selectList("score.listScore", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Score readScore(String hak) throws Exception {
		Score dto=null;
		try {
			dto=dao.selectOne("score.readScore", hak);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void updateScore(Score dto) throws Exception {
		try {
			dao.updateData("score.updateScore", dto);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;
		}
	}

	@Override
	public void deleteScore(String hak) throws Exception {
		try {
			dao.deleteData("score.deleteScore", hak);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;
		}
	}
}
