package com.sp.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class LogAspect {
	@Pointcut(value="execution(* com.sp..*.*(..))")
	private void allMethod() {
		
	}
	
	@Around("allMethod()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
		
		// 메소드 실행 전
		StopWatch watch = new StopWatch();
		watch.start(pjp.toString());
		
		Object ret = pjp.proceed();
		
		// 메소드 실행 후
		watch.stop();
		//log.info(watch.prettyPrint());
		log.debug(watch.prettyPrint());
		
		return ret;
	}
	
	@AfterThrowing(value="allMethod()", throwing="e")
	public void execptionHanler(JoinPoint jp, Exception e) throws Exception {
		String method = jp.toString();
		String msg = e.getMessage();
		
		Logger log = LoggerFactory.getLogger(jp.getTarget().getClass());
		
		/*
		 * Try ~ Catch(Exception e) {} 에서 예외가 발생하여 catch하면 로그는 출력 안됨
		 * Try ~ Catch(Exception e) {throw e;} 예외를 던져야 로그 출력 
		 */
		
		log.error("==========" + method + " : " + msg);
	}
}
