package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.DTO.User_DTO;
import app.database.ConnectDatabase;

public class Login_DAL {
	ConnectDatabase CNDB = new ConnectDatabase();
	public User_DTO selectByUserName(String username) throws SQLException {
		User_DTO result = null;
		
		try {
			Connection conn = (Connection) CNDB.connectToDB();
	        String query = "SELECT * FROM users WHERE userName=?";
	        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
	        pst.setString(1, username);
	        ResultSet rs = (ResultSet) pst.executeQuery();
	        
	        while(rs.next()){
	        	int id = rs.getInt("userID");
	            String userName = rs.getString("userName");
	            String userEmail = rs.getString("userEmail");
	            String userPassword = rs.getString("userPassword");
	            String userFullName = rs.getString("userFullName");
	            int isAdmin = rs.getInt("isAdmin");
	            User_DTO tk = new User_DTO(id,userName, userEmail, userPassword,
	            		userFullName,isAdmin);
	            result = tk;
	        }
	        
	        
	        CNDB.closeConnect();
			
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
}
