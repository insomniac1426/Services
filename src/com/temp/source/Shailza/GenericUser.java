package com.temp.source.Shailza;

import java.sql.Date;


public class GenericUser {
	private String username;
	private String password;
	private Date lastLogin;
	private String userType;
	private int isActive;
	private int isConfirmed;
	
	
	public GenericUser(String username, String password, Date lastLogin, String userType, int isActive,
			int isConfirmed) {
		this.username = username;
		this.password = password;
		this.lastLogin = lastLogin;
		this.userType = userType;
		this.isActive = isActive;
		this.isConfirmed = isConfirmed;
	}
	
	
	
	@Override
	public String toString() {
		return "GenericUser [username=" + username + ", userType=" + userType + "]";
	}



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int isActive() {
		return isActive;
	}
	public void setActive(int isActive) {
		this.isActive = isActive;
	}
	public int isConfirmed() {
		return isConfirmed;
	}
	public void setConfirmed(int isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	
	
	
	
	
	
	
}
