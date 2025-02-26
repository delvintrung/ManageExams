package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DTO.Answer_DTO;
import app.database.ConnectDatabase;

public class Answer_DAL {
	public List<String> getAnswerByID(int qID) {
        try {
        	List<String> result = new ArrayList<>();
        	ConnectDatabase db = new ConnectDatabase(); 
            Connection conn = (Connection) db.connectToDB();
            String query = "SELECT awContent FROM answers where qID = ?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, qID);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String content = rs.getString("awContent");
                result.add(content);
            }
            db.closeConnect();
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
		return null;
		
	}
	
	public int insertAnswerText(int qID, String content, int isRight) {
		ConnectDatabase db = new ConnectDatabase(); 
		int result = 0;
        Connection conn;
		try {
			conn = (Connection) db.connectToDB();
			 String query = "insert into answers(qID, awContent, isRight) value (?, ?, ?)";
		        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
		        pst.setInt(1, qID);
		        pst.setString(2, content);
		        pst.setInt(3, isRight);
		        result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
       
	}
	
	public int insertAnswerImage(int qID, String content, int isRight) {
		ConnectDatabase db = new ConnectDatabase(); 
		int result = 0;
        Connection conn;
		try {
			conn = (Connection) db.connectToDB();
			 String query = "insert into answers(qID, awPictures, isRight) value (?, ?, ?)";
		        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
		        pst.setInt(1, qID);
		        pst.setString(2, content);
		        pst.setInt(3, isRight);
		        result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
       
	}
}
