package com.sp.member;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("loginMember")
@Scope("session")
public class SessionInfo {
	private String userId;
	private String userName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
