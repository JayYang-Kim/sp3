package com.sp.demo;

import java.util.Map;

public interface DemoService {
	// 트랜잭션 처리 시 메소드 Return값은 void로 해야한다. (예외를 던져줘야함)
	public void insertDemo(Map<String, Object> map) throws Exception;
}
