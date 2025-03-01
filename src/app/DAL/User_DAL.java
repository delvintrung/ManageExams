package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.DTO.User_DTO;
import app.database.ConnectDatabase;

public class User_DAL {
	public ArrayList<User_DTO> allUser = new ArrayList<>();
	
	public ArrayList<User_DTO> getUsers() {
		ArrayList<User_DTO> list = null;
		
		try {
			ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            
            String sql = "SELECT * FROM `users` WHERE `uStatus`=1";
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            
            list = new ArrayList<User_DTO>();
            
            while(rs.next()){
            	int id = rs.getInt("userID");
                String username = rs.getString("userName");
                String email = rs.getNString("userEmail");
                String password = rs.getString("userPassword");
                String fullname = rs.getString("userFullName");
                int isAdmin = rs.getInt("isAdmin");
                
                User_DTO nv = new User_DTO(id, username, email, password, fullname, isAdmin);
                list.add(nv);
            }
            
            rs.close();
	        pst.close();
	        conn.close();
		} catch (SQLException ex) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return list;
	}
	
	public User_DTO getUser(int id) {
	    User_DTO user = null;
	    
	    try {
	        ConnectDatabase db = new ConnectDatabase();
	        Connection conn = db.connectToDB();

	        String sql = "SELECT * FROM `users` WHERE `userID`=? and `uStatus`=1";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setInt(1, id);

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            user = new User_DTO();
	            user.setUserID(rs.getInt("userID"));
	            user.setUserFullName(rs.getString("userFullName"));
	            user.setUserEmail(rs.getString("userEmail"));
	            user.setUserPassword(rs.getString("userPassword"));
	            user.setIsAdmin(rs.getInt("isAdmin"));
	        }

	        rs.close();
	        pst.close();
	        conn.close();
	    } catch (SQLException ex) {
	        Logger.getLogger(User_DAL.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	    return user;
	}
	
	public User_DTO getUser(String username) {
	    User_DTO user = null;
	    
	    try {
	        ConnectDatabase db = new ConnectDatabase();
	        Connection conn = db.connectToDB();

	        String sql = "SELECT * FROM `users` WHERE `userName`=? and `uStatus`=1";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setString(1, username);

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            user = new User_DTO();
	            user.setUserID(rs.getInt("userID"));
	            user.setUserFullName(rs.getString("userFullName"));
	            user.setUserEmail(rs.getString("userEmail"));
	            user.setUserName(rs.getString("userName"));
	            user.setUserPassword(rs.getString("userPassword"));
	            user.setIsAdmin(rs.getInt("isAdmin"));
	        }

	        rs.close();
	        pst.close();
	        conn.close();
	    } catch (SQLException ex) {
	        Logger.getLogger(User_DAL.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	    return user;
	}
	
	public ArrayList<User_DTO> search(String keyword) {
	    ArrayList<User_DTO> users = new ArrayList<>();
	    
	    try {
	        ConnectDatabase db = new ConnectDatabase();
	        Connection conn = db.connectToDB();

	        String sql = "SELECT * FROM users WHERE `userName` LIKE ? OR `userEmail` LIKE ? OR `userFullName` LIKE ? AND `uStatus`=1";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        String searchPattern = "%" + keyword + "%";
	        pst.setString(1, searchPattern);
	        pst.setString(2, searchPattern);
	        pst.setString(3, searchPattern);

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            User_DTO user = new User_DTO();
	            user.setUserID(rs.getInt("userID"));
	            user.setUserName(rs.getString("userName"));
	            user.setUserEmail(rs.getString("userEmail"));
	            user.setUserFullName(rs.getString("userFullName"));
	            user.setIsAdmin(rs.getInt("isAdmin"));
	            users.add(user);
	        }

	        rs.close();
	        pst.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return users;
	}


	public int create(User_DTO user) {
		int res = 0;
		
        try {
        	ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            
            String sql = "INSERT INTO `users`(`userName`, `userEmail`, `userPassword`, `userFullName`, `isAdmin`, `uStatus`) VALUES (?,?,?,?,?,?)";
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getUserEmail());
            pst.setString(3, user.getUserPassword());
            pst.setString(4, user.getUserFullName());
            pst.setInt(5, user.getIsAdmin());
            pst.setInt(6, 1);
            
            res = pst.executeUpdate();
            
	        pst.close();
	        conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
	
	public int update(User_DTO user) {
		int res = 0;
		
		try {
			ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            
            String sql = "UPDATE `users` SET `userEmail`=?, `userPassword`=?, `userFullName`=? WHERE `userID`=?";
			  
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, user.getUserEmail());
			pst.setString(2, user.getUserPassword());
			pst.setString(3, user.getUserFullName());
			pst.setInt(4, user.getUserID());
			
			res = pst.executeUpdate();
			
			pst.close();
	        conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return res;
	}
	
	public int delete(int id) {
		int res = 0;
		
		try {
			ConnectDatabase db = new ConnectDatabase();
			Connection conn = db.connectToDB();
			  
			String sql = "UPDATE `users` SET `uStatu`=0 WHERE `userID`=?";
			  
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, id);
			  
			res = pst.executeUpdate();
			  
			pst.close();
			conn.close();
		} catch (Exception e) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return res;
	}
}
