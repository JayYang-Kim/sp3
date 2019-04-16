package com.sp.member;

import javax.inject.Inject;
import javax.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("member.loginService")
public class LoginServiceImpl implements LoginService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Inject // 자바에서 지원 (타입에 의한 의존관계 설정)
	private Provider<SessionInfo> provider; // 세선 정보를 가져옴
	
	@Override
	public SessionInfo loginMemberInfo() {
		SessionInfo info = null;
		
		try {
			// 세션마다 생성된 객체를 저장하고 처리
			SessionInfo sessionUser = provider.get();
			// provider : Singleton Bean에서 Prototype Bean을 참고하고자 할때 적용
			// @Scope("session")은 Singleton Bean을 이용하여 객체를 참조할 수 없음 (세션마다 객체가 생성되기 때문)
			// @Autowired로 의존관계를 설정하면 안되고 @Inject를 이용하여 의존관계를 설정해야한다.
			
			if(sessionUser.getUserId() != null) {
				info = sessionUser;
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return info;
	}

	@Override
	public boolean requestLogin(String userId, String userPwd) {
		boolean isLogin = false;
		
		try {
			// DB에서 데이터를 불어와 아이디와 패스워드 비교
			if(userId.equals("spring") && userPwd.equals("spring")) {
				SessionInfo sessionUser = provider.get();
				sessionUser.setUserId("spring");
				sessionUser.setUserName("스프링");
				
				isLogin = true;
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return isLogin;
	}

}
