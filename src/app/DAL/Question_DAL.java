package app.DAL;

import java.sql.Connection;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import app.DTO.Answer_DTO;
import app.DTO.Question_DTO;
import app.DTO.Topic_DTO;
import app.Helper.ComboItem;
import app.database.ConnectDatabase;

public class Question_DAL {
	public Question_DTO getQuestion(int id) {
		Question_DTO question = null;
		
		try {
			ConnectDatabase db = new ConnectDatabase();
            Connection conn = db.connectToDB();
            
            String query = "SELECT * FROM `questions` WHERE `qID` = ? AND `qStatus` = 1";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, id);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            
            if (rs.next()) {
            	question = new Question_DTO();
            	question.setqID(rs.getInt("qID"));
            	question.setqContent(rs.getString("qContent"));
            	question.setqPicture(rs.getString("qPictures"));
            	question.setqTopicID(rs.getInt("qTopicID"));
            	question.setqLevel(rs.getString("qLevel"));
            }
            
            rs.close();
	        pst.close();
	        conn.close();
		} catch (SQLException e) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return question;
	}
	
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
            String sql = "SELECT qID\r\n"
            		+ "FROM questions\r\n"
            		+ "ORDER BY qID DESC\r\n"
            		+ "LIMIT 1;";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs.next()) {
                    result = rs.getInt("qID");
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

    public List<Integer> getRandomQuestions(List<Integer> topics, int numEasy, int numMedium, int numDiff) throws SQLException {
        List<Integer> questionIds = new ArrayList<>();
        Connection conn = new ConnectDatabase().connectToDB();

        String topicIdString = topics.toString().replace("[", "(").replace("]", ")");


        String query = "SELECT qID FROM questions WHERE qTopicID IN " + topicIdString + " AND qLevel = ? ORDER BY RAND() LIMIT ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "easy");
            ps.setInt(2, numEasy);
            ResultSet rsEasy = ps.executeQuery();
            while (rsEasy.next()) {
                questionIds.add(rsEasy.getInt("qID"));
            }

            ps.setString(1, "medium");
            ps.setInt(2, numMedium);
            ResultSet rsMedium = ps.executeQuery();
            while (rsMedium.next()) {
                questionIds.add(rsMedium.getInt("qID"));
            }

            ps.setString(1, "hard");
            ps.setInt(2, numDiff);
            ResultSet rsDiff = ps.executeQuery();
            while (rsDiff.next()) {
                questionIds.add(rsDiff.getInt("qID"));
            }
        }

        return questionIds;
    }
}
