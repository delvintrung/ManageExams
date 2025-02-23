package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
