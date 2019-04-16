package com.sp.member;

public interface LoginService {
	public SessionInfo loginMemberInfo();
	public boolean requestLogin(String userId, String userPwd);
}
