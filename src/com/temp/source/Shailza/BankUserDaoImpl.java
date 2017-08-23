package com.temp.source.Shailza;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankUserDaoImpl {
	/*The object of ResultSet maintains a cursor pointing to a row of a table.
Initially, cursor points to before the first row.*/
	
	
	
	private static ResultSet res = null;
	
	
	
	private static BankUser convertResToObject() throws SQLException{
		BankUser gu = null;
		if(res != null) {
			while(res.next()) {
				
				String Fullname= res.getString("1");
				String address= res.getString("2");
				String p_group = res.getString("3");
				gu = new BankUser(Fullname,address,p_group);
			}
		}
		return gu;
	}
	
	public static boolean insertIntoBankUser(BankUser c) throws SQLException {
		
		//if(checkUsernameExistence(c.email)) { return false; }
		
		Connection conn = SQLConnection.getConnection();
		/*PreparedStatement for parameterized query like insert();*/
		PreparedStatement st = conn.prepareStatement("INSERT INTO Bank_user values(?,?,?)");
		st.setString(1, c.Fullname);
		st.setString(2, c.address);
		st.setString(3, c.p_group);
		st.executeUpdate();
		return true;
	}
	
	
	

	
}
