package com.anmol.demo;

import java.sql.Date;


public class GenericUser {
	private String username;
	private String password;
	private boolean userType;
	private boolean isActive;
	private boolean isConfirmed;
	
	
	public GenericUser(String username, String password, boolean isActive,
			boolean isConfirmed, boolean userType) {
		this.username = username;
		this.password = password;
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
	
	public boolean getUserType() {
		return userType;
	}
	public void setUserType(boolean userType) {
		this.userType = userType;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isConfirmed() {
		return isConfirmed;
	}
	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	
	
	
	
	
	
	
}
