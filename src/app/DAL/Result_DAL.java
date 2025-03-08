package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.DTO.Result_DTO;
import app.database.ConnectDatabase;

public class Result_DAL {
	public ArrayList<Result_DTO> getUserResults(int userId) {
		ArrayList<Result_DTO> list = new ArrayList<Result_DTO>();
		
		try {
			ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            
            String sql = "SELECT * FROM `result` WHERE `userID`=? ORDER BY `rs_date` DESC";
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, userId);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            
            while (rs.next()) {
            	Result_DTO result = new Result_DTO();
            	result.setRs_num(rs.getInt("rs_num"));
            	result.setUserID(rs.getInt("userID"));
            	result.setExCode(rs.getString("exCode"));
            	result.setRs_anwsers(rs.getString("rs_answers"));
            	result.setRs_mark(rs.getFloat("rs_mark"));
            	result.setRs_date(rs.getString("rs_date"));
            	
            	list.add(result);
            }
            
            rs.close();
	        pst.close();
	        conn.close();
		} catch (SQLException e) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return list;
	}
}
