package com.sp.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("demo.demoService")
public class DemoServiceImpl implements DemoService {

	@Autowired
	private CommonDAO dao;
	
	@Override
	public void insertDemo(Map<String, Object> map) throws Exception {
		// 트랜잭션 처리를 위해서는 반드시 try ~ catch(예외처리) 해야 함
		try {
			// 트랜잭션 작업이 필요한 것은 이곳에서 한 번에 처리한다.
			dao.insertData("demo.insertDemo1", map);
			dao.insertData("demo.insertDemo2", map);
			dao.insertData("demo.insertDemo3", map);
		} catch (Exception e) {
			System.out.println(e.toString());
			throw e; // 반드시 예외를 throw 해야 트랜잭션 처리가 가능
		}
	}

}
