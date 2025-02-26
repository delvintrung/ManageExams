package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.DTO.Topic_DTO;
import app.database.ConnectDatabase;

public class Topic_DAL {
	public ArrayList<Topic_DTO> getTopics() {
		try {
			ConnectDatabase db = new ConnectDatabase();
			Connection conn = db.connectToDB();
			
			ArrayList<Topic_DTO> result = new ArrayList<Topic_DTO>();
			
			String query = "SELECT * FROM topics where tpStatus = 1";
			
			ResultSet rs = conn.createStatement().executeQuery(query);
			
			while (rs.next()) {
				int tpID = rs.getInt("tpID");
				String tpTitle = rs.getString("tpTitle");
				int tpParent = rs.getInt("tpParent");
				int tpStatus = rs.getInt("tpStatus");
				result.add(new Topic_DTO(tpID, tpTitle, tpParent, tpStatus));
			}
			
			db.closeConnect();
			
			return result.size() > 0 ? result : null;
		} catch (Exception e) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
	
	public List<Topic_DTO> getParentTopic() throws SQLException {
		  ConnectDatabase db = new ConnectDatabase();
		  Connection conn = db.connectToDB();
		  
		  List<Topic_DTO> result = new ArrayList<Topic_DTO>();
		  
		  String query = "SELECT * FROM topics where tpParent = 0";
		  
		  ResultSet rs = conn.createStatement().executeQuery(query);
		  
		  while (rs.next()) {
			  int tpID = rs.getInt("tpID");
			  String tpTitle = rs.getString("tpTitle");
			  int tpParent = rs.getInt("tpParent");
			  int tpStatus = rs.getInt("tpStatus");
			  result.add(new Topic_DTO(tpID, tpTitle, tpParent, tpStatus));
	        }

	        if(result.size() > 0 ) {
	        	return result;
	        }
	        return null;
	    }
	    
	    
	    public List<Topic_DTO> getChildTopic() throws SQLException {
	    	ConnectDatabase db = new ConnectDatabase();
	    	List<Topic_DTO> result = new ArrayList<Topic_DTO>();
	        Connection conn = db.connectToDB();
	        String query = "SELECT * FROM topics where tpParent > 0";
	        ResultSet rs = conn.createStatement().executeQuery(query);
	        while (rs.next()) {
	            int tpID = rs.getInt("tpID");
	            String tpTitle = rs.getString("tpTitle");
	            int tpParent = rs.getInt("tpParent");
	            int tpStatus = rs.getInt("tpStatus");
	            result.add(new Topic_DTO(tpID,tpTitle,tpParent,tpStatus));
	        }
	        if(result.size() > 0 ) {
	        	return result;
	        }
	        return null;
	    }
	  
	  public int create(Topic_DTO topic) {
		  try {
			  ConnectDatabase db = new ConnectDatabase();
			  Connection conn = db.connectToDB();
			  
			  String sql = "INSERT INTO `topics`(`tpTitle`, `tpParent`, `tpStatus`) VALUES (?,?,?)";
			  
			  PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			  pst.setString(1, topic.getTpTitle());
			  pst.setInt(2, topic.getTpParent());
			  pst.setInt(3, 1);
			  
			  return pst.executeUpdate();
		  } catch (Exception e) {
			  Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
			return 0;
		  }
	  }
	  
	  public int update(Topic_DTO topic) {
		  try {
			  ConnectDatabase db = new ConnectDatabase();
			  Connection conn = db.connectToDB();
			  
			  String sql = "UPDATE `topics` SET `tpTitle`=?,`tpParent`=? WHERE `tpID`=?";
			  
			  PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			  pst.setString(1, topic.getTpTitle());
			  pst.setInt(2, topic.getTpParent());
			  pst.setInt(3, topic.getTpID());
			  
			  return pst.executeUpdate();
		  } catch (Exception e) {
			  Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
			  return 0;
		  }
	  }
	  
	  public int delete(int id) {
		  try {
			  ConnectDatabase db = new ConnectDatabase();
			  Connection conn = db.connectToDB();
			  
			  String sql = "UPDATE `topics` SET `tpStatus`= 0 WHERE tpID = ?";
			  
			  PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			  pst.setInt(1, id);
			  
			  return pst.executeUpdate();
		  } catch (Exception e) {
			  Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
			  return 0;
		  }
	  }
}
