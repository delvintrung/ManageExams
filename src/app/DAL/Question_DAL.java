package app.DAL;

import java.sql.Connection;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import app.DTO.Question_DTO;
import app.database.ConnectDatabase;

public class Question_DAL {
	
	public ArrayList<Question_DTO> selectAll() {
        ArrayList<Question_DTO> result = new ArrayList<Question_DTO>();
        try {
        	
        	ConnectDatabase db = new ConnectDatabase();
            Connection conn = db.connectToDB();
            String query = "SELECT * FROM questions";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("qID");
                String ho = rs.getString("qContent");
                String ten = rs.getNString("qPictures");
                int gioiTinh = rs.getInt("qTopicID");
                String sdt = rs.getString("qLevel");
                int email = rs.getInt("qStatus");
                Question_DTO nv = new Question_DTO(id, ho, ten, gioiTinh, sdt, email);
                result.add(nv);
            }
            db.closeConnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
	
	public int getAutoIncrement() {
        int result = -1;
        try {
        	ConnectDatabase db = new ConnectDatabase();
            Connection con = (Connection) db.connectToDB();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'manageexams' AND TABLE_NAME = 'questions'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs.next()) {
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
	
	
	public int delete(int id) {
        int result = 0;
        try {
        	ConnectDatabase db = new ConnectDatabase();
            Connection con = (Connection) db.connectToDB();
            String sql = "UPDATE `questions` SET `qStatus`= 0 WHERE qID = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            db.closeConnect();;
        } catch (SQLException ex) {
            Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
	
	
    public int update(Question_DTO q) {
        int result = 0;
        try {
        	ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            String query = "UPDATE `questions` SET `qContent`=?,`qPictures`=?,`qTopicID`=?,`qLevel`=?,`qStatus`=?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setString(1, q.getqContent());
            pst.setString(2, q.getqPicture());
            pst.setFloat(3, q.getqTopicID());
            pst.setString(4, q.getqLevel());
            pst.setInt(5, q.getqStatus());
            result = pst.executeUpdate();
            db.closeConnect();
        } catch (SQLException ex) {
            Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
	
	public int insert(Question_DTO q) {
        int result = 0;
        try {
        	ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            String sql = "INSERT INTO `questions`(`qContent`, `qPictures`, `qTopicID`, `qLevel`, `qStatus`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, q.getqContent());
            pst.setString(2, q.getqPicture());
            pst.setInt(3, q.getqTopicID());
            pst.setString(4, q.getqLevel());
            pst.setInt(5, q.getqStatus());
            result = pst.executeUpdate();
            db.closeConnect();
        } catch (SQLException ex) {
            Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
	
	public List<Question_DTO> getRandomQuestion(String topicIds, int numQues) throws SQLException {
			List<Question_DTO> result = new ArrayList<Question_DTO>();
			String query = "SELECT * FROM questions WHERE qTopicID IN " + topicIds + " ORDER BY RAND() LIMIT ?";
			Connection conn = new ConnectDatabase().connectToDB();
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);

			pst.setInt(1, numQues);
			
			
			ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("qID");
                String ho = rs.getString("qContent");
                String ten = rs.getNString("qPictures");
                int gioiTinh = rs.getInt("qTopicID");
                String sdt = rs.getString("qLevel");
                int email = rs.getInt("qStatus");
                Question_DTO nv = new Question_DTO(id, ho, ten, gioiTinh, sdt, email);
                result.add(nv);
            }
            
            return result;

	}
	
	
	
	
	public List<Integer> getRandomQuestions(int testID, int numEasy, int numMedium, int numDiff) throws SQLException {
	    List<Integer> questionIds = new ArrayList<>();
	    Connection conn = new ConnectDatabase().connectToDB();

	    
	    String queryTpID = "SELECT tpID FROM test WHERE testID = ?";
	    int tpID = -1;

	    try (PreparedStatement psTp = conn.prepareStatement(queryTpID)) {
	        psTp.setInt(1, testID);
	        ResultSet rsTp = psTp.executeQuery();
	        if (rsTp.next()) {
	            tpID = rsTp.getInt("tpID");
	        }
	    }

	    if (tpID == -1) {
	        System.out.println("Không tìm thấy chủ đề của bài thi.");
	        return questionIds;
	    }

	    
	    String query = "SELECT qID FROM questions WHERE qTopicID = ? AND qLevel = ? ORDER BY RAND() LIMIT ?";
	    
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, tpID);

	        ps.setString(2, "easy");
	        ps.setInt(3, numEasy);
	        ResultSet rsEasy = ps.executeQuery();
	        while (rsEasy.next()) {
	            questionIds.add(rsEasy.getInt("qID"));
	        }

	        ps.setString(2, "medium");
	        ps.setInt(3, numMedium);
	        ResultSet rsMedium = ps.executeQuery();
	        while (rsMedium.next()) {
	            questionIds.add(rsMedium.getInt("qID"));
	        }

	        ps.setString(2, "diff");
	        ps.setInt(3, numDiff);
	        ResultSet rsDiff = ps.executeQuery();
	        while (rsDiff.next()) {
	            questionIds.add(rsDiff.getInt("qID"));
	        }
	    }

	    return questionIds;
	}


}
