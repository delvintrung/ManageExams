package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DTO.Exam_DTO;
import app.DTO.Result_DTO;
import app.database.ConnectDatabase;

public class Result_DAL {
static ConnectDatabase db;
    
    public List<Exam_DTO> getAllExam() throws SQLException {
    	List<Exam_DTO> result = new ArrayList<Exam_DTO>();
    	db = new ConnectDatabase();
        Connection conn = db.connectToDB();
        String query = "SELECT * FROM exams";
//        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = conn.createStatement().executeQuery(query);
        while (rs.next()) {
            String testCode = rs.getString("testCode");
            String exOrder = rs.getString("exOrder");
            String exCode = rs.getString("exCode");
            String exQues = rs.getString("ex_quesIDs");
            result.add(new Exam_DTO(testCode,exOrder,exCode,exQues));
        }
        if(result.size() > 0 ) {
        	return result;
        }
        return null;
    }
    
    public int insertResult(Result_DTO newResult) {
    	int result = 0;
    	db = new ConnectDatabase();
        try {
			Connection conn = db.connectToDB();
			
			String query = "insert into result(userID,exCode,rs_answers, rs_mark, rs_date) value (?, ?, ?, ?, NOW())";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, newResult.getUserID());
			pst.setString(2, newResult.getExCode());
			pst.setString(3, String.join(",", newResult.getRs_anwsers()));
			pst.setDouble(4, newResult.getRs_mark());
			
			result = pst.executeUpdate();
			db.closeConnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
}
