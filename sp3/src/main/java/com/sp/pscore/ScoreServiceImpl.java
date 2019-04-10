package com.sp.pscore;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.MyUtil;
import com.sp.common.dao.CommonDAO;

@Service("score.scoreService")
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	private CommonDAO dao;
	
	@Override
	public int insertScore(Score dto) {
		int result = 0;
		
		try {
			/**
			 * 프로시져는 update() 메소드를 이용하여 실행하며, 리턴 값은 프로시져 실행 여부이며
			 * 추가나 수정 삭제등의 개수가 아니다.
			 */
			dao.callUpdateProcedure("pscore.insertScore", dto);
			
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updateScore(Score dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteScore(String hak) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dataCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Score readScore(String hak) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Score> listScore(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
