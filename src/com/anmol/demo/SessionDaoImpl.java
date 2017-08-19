package com.anmol.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionDaoImpl {
	
	private static ResultSet res = null;
	
	private static Session convertResToObject() throws SQLException{
		Session s = null;
		if(res != null) {
			while(res.next()) {
				String username = res.getString(1);
				String token = res.getString(2);
				String userType = res.getString(3);
				s = new Session(token, username, userType);
			}
		}
		return s;
	}
	
	public static void deleteSessionFromTable(String username) throws SQLException{
		Connection conn = SQLConnection.getConnection();
		PreparedStatement st = conn.prepareStatement("DELETE FROM sessions WHERE userid='" + username +"'");
		st.executeUpdate();
	}
	
	public static void addSessionToTable(Session s) throws SQLException{
		if(checkSessionPresence(s.userId)){
			deleteSessionFromTable(s.userId);
		}
		Connection conn = SQLConnection.getConnection();
		PreparedStatement st = conn.prepareStatement("INSERT INTO sessions values" + "(?,?,?)");
		st.setString(1, s.userId);
		st.setString(2, s.token);
		st.setString(3, s.userType);
		st.executeUpdate();
	}
	
	public static boolean checkSessionPresence(String username) throws SQLException{
		res = null;
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			String queryString = "select * from sessions where UserID='" + username + "'";
			res = st.executeQuery(queryString);
		}
		if(res == null)	return false;
		return true;
	}
	
	public static boolean confirmSession(String token, String username) throws SQLException{
		if(checkSessionPresence(username)){
			Session s = convertResToObject();
			if(s == null)	{ return false; }
			if(s.token.equals(token))	{ return true; }
		}
		return false;
	}
	
}
