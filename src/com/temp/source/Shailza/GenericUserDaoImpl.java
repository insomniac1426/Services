package com.temp.source.Shailza;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericUserDaoImpl{

	private static ResultSet res = null;
	
	private static GenericUser convertResToObject() throws SQLException{
		GenericUser gu = null;
		if(res != null) {
			while(res.next()) {
				String uID = res.getString(1);
				String password = res.getString(2);
				Date lastLogin = res.getDate(3);
				String userType = res.getString(4);
				int isActive = res.getInt(5);
				int isConfirmed = res.getInt(6);
				gu = new GenericUser(uID, password, lastLogin, userType, isActive, isConfirmed);
			}
		}
		return gu;
	}
	
	public static GenericUser searchUser(String username) throws SQLException{
		res = null;
		Connection conn = SQLConnection.getConnection();
		if(conn != null) {
			Statement st = conn.createStatement();
			String queryString = "select * from GenUserTable where UserID='" + username + "'";
			res = st.executeQuery(queryString);
			GenericUser gu = convertResToObject();
			return gu;
		}
		return null;
	}
}
