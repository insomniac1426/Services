package com.anmol.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class UserProductsDao {

	
	public static boolean insertIntoProducts(UserProducts up) throws SQLException {
			int i=0;
			Connection conn = SQLConnection.getConnection();
			for (i=0; i<up.userProducts.length; i++)
			{PreparedStatement st = conn.prepareStatement("INSERT INTO /'User_products/' values(?,?)");
			st.setString(1, up.username);
			st.setString(2, up.userProducts[i]);
			st.executeUpdate();
			}
			
			return true;
	}
}