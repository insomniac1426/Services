package com.anmol.demo;

public class Session {
	String token;
	String userId;
	String userType;
	
	public Session(String token, String userId, String userType) {
		this.token = token;
		this.userId = userId;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Session [token=" + token + ", userId=" + userId + ", userType=" + userType + "]";
	}
	
	
}
