package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.DTO.User_DTO;
import app.database.ConnectDatabase;

public class User_DAL {
	public ArrayList<User_DTO> allUser = new ArrayList<>();
	
	public int create(User_DTO user) {
        int result = 0;
        
        try {
        	ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            
            String sql = "INSERT INTO `users`(`userName`, `userEmail`, `userPassword`, `userFullName`, `isAdmin`) VALUES (?,?,?,?,?)";
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getUserEmail());
            pst.setString(3, user.getUserPassword());
            pst.setString(4, user.getUserFullName());
            pst.setInt(5, user.getIsAdmin());
            
            result = pst.executeUpdate();
            db.closeConnect();
        } catch (SQLException ex) {
            Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
