package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.DTO.Answer_DTO;
import app.DTO.User_DTO;
import app.database.ConnectDatabase;

public class Answer_DAL {
	public ArrayList<Answer_DTO> getQuestionAnswers(int questionId) {
		ArrayList<Answer_DTO> list = new ArrayList<Answer_DTO>();
		
		try {
			ConnectDatabase db = new ConnectDatabase(); 
            Connection conn = (Connection) db.connectToDB();
            
            String query = "SELECT * FROM `answers` WHERE `qID` = ? AND `awStatus` = 1";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            pst.setInt(1, questionId);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            
            while (rs.next()) {
            	Answer_DTO answer = new Answer_DTO();
            	answer.setAwID(rs.getInt("awID"));
            	answer.setqID(rs.getInt("qID"));
            	answer.setAwContent(rs.getString("awContent"));
            	answer.setAwPictures(rs.getString("awPictures"));
            	answer.setIsRight(rs.getInt("isRight"));
            	
            	list.add(answer);
            }
            
            rs.close();
	        pst.close();
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<Answer_DTO> search(String keyword) {
	    ArrayList<Answer_DTO> ans = new ArrayList<>();
	    
	    try {
	        ConnectDatabase db = new ConnectDatabase();
	        Connection conn = db.connectToDB();

	        String sql = "SELECT * FROM ans WHERE `awContent` LIKE ? OR `userEmail` LIKE ? AND `uStatus`=1";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        String searchPattern = "%" + keyword + "%";
	        pst.setString(1, searchPattern);
	        pst.setString(2, searchPattern);

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            Answer_DTO an = new Answer_DTO();
	            an.setAwID(rs.getInt("awID"));
	            an.setAwContent(rs.getString("awContent"));;
	            an.setAwPictures(rs.getString("awPictures"));
	            an.setIsRight(rs.getInt("isRight"));
	            an.setqID(0);
	            ans.add(an);
	        }

	        rs.close();
	        pst.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ans;
	}
	
	public ArrayList<Answer_DTO> getAnswers() {
		ArrayList<Answer_DTO> list = new ArrayList<Answer_DTO>();
		
		try {
			ConnectDatabase db = new ConnectDatabase(); 
            Connection conn = (Connection) db.connectToDB();
            
            String query = "SELECT * FROM `answers` WHERE `awStatus` = 1";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
            
            ResultSet rs = (ResultSet) pst.executeQuery();
            
            while (rs.next()) {
            	Answer_DTO answer = new Answer_DTO();
            	answer.setAwID(rs.getInt("awID"));
            	answer.setqID(rs.getInt("qID"));
            	answer.setAwContent(rs.getString("awContent"));
            	answer.setAwPictures(rs.getString("awPictures"));
            	answer.setIsRight(rs.getInt("isRight"));
            	
            	list.add(answer);
            }
            
            rs.close();
	        pst.close();
	        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	 
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
	
	public int create(Answer_DTO ans) {
		int res = 0;
		
        try {
        	ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            
            String sql = "INSERT INTO `answers`(`qID`, `awContent`, `awPictures`, `isRight`, `awStatus`) VALUES (?,?,?,?,?)";
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, ans.getqID());
            pst.setString(2, ans.getAwContent());
            pst.setString(3, ans.getAwPictures());
            pst.setInt(4, ans.getIsRight());
            pst.setInt(5, 1);
            
            res = pst.executeUpdate();
            
	        pst.close();
	        conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
	
	public int update(Answer_DTO ans) {
		int res = 0;
		
		try {
			ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            
            String sql = "UPDATE `answers` SET `awContent`=?, `isRight`=?, `awStatus`=? WHERE `awID`=?";
			  
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, ans.getAwContent());
			pst.setInt(2, ans.getIsRight());
			pst.setInt(3, ans.getAwStatus());
			pst.setInt(4, ans.getAwID());
			
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
			  
			String sql = "UPDATE `users` SET `awStatus`=0 WHERE `awID`=?";
			  
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
