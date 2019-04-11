package com.sp.pscore;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("pscore.scoreService")
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
		int result = 0;
		
		try {
			dao.callUpdateProcedure("pscore.updateScore", dto);
			
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int deleteScore(String hak) {
		int result = 0;
		
		try {
			dao.callUpdateProcedure("pscore.deleteScore", hak);
			
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int dataCount() {
		int result = 0;
		
		try {
			Map<String, Object> map = new HashedMap<>();
			dao.callSelectOneProcedureMap("pscore.dataCount", map);
			result = (Integer)map.get("result");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Score readScore(String hak) {
		Score dto = null;
		
		try {
			Map<String, Object> map = new HashedMap<>();
			map.put("hak", hak);
			
			dao.callSelectListProcedureMap("pscore.readScore", map);
			List<Score> list = (List<Score>)map.get("result");
			
			if(list != null && list.size() > 0) {
				dto = (Score)list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	/*
	 * map.get을 호출하면 결과값은 object형으로 return된다.
	 * 제네릭으로 다운캐스팅을하면 소스에 경고가 뜨게 된다.
	 * 경고가 뜰 경우 @SuppressWarnings("unchecked")을 사용하여 경고가 안뜨게 처리
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Score> listScore(Map<String, Object> map) {
		List<Score> list = null;
		
		try {
			dao.callSelectListProcedureMap("pscore.listScore", map);
			list = (List<Score>)map.get("result");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
