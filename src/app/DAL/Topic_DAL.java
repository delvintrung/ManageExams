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
		ArrayList<Topic_DTO> result = null;
		
		try {
			ConnectDatabase db = new ConnectDatabase();
			Connection conn = db.connectToDB();
			
			result = new ArrayList<Topic_DTO>();
			
			String query = "SELECT * FROM topics where `tpStatus` = 1";
			
			ResultSet rs = conn.createStatement().executeQuery(query);
			
			while (rs.next()) {
				int tpID = rs.getInt("tpID");
				String tpTitle = rs.getString("tpTitle");
				int tpParent = rs.getInt("tpParent");
				int tpStatus = rs.getInt("tpStatus");
				result.add(new Topic_DTO(tpID, tpTitle, tpParent, tpStatus));
			}
			
			db.closeConnect();
			rs.close();
		} catch (Exception e) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return result;
	}
	
	public List<Topic_DTO> getParentTopic() throws SQLException {
		  ConnectDatabase db = new ConnectDatabase();
		  Connection conn = db.connectToDB();
		  
		  List<Topic_DTO> result = new ArrayList<Topic_DTO>();
		  
		  String query = "SELECT * FROM topics where tpParent > 0";
		  
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
	
	public ArrayList<Topic_DTO> search(String keyword) {
	    ArrayList<Topic_DTO> topicList = null;
	    
	    try {
	        ConnectDatabase db = new ConnectDatabase();
	        Connection conn = db.connectToDB();

	        String sql = "SELECT * FROM topics WHERE `tpTitle` LIKE ? and `tpStatus`=1";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setString(1, "%" + keyword + "%");

	        ResultSet rs = pst.executeQuery();
	        topicList = new ArrayList<>();

	        while (rs.next()) {
	            Topic_DTO topic = new Topic_DTO();
	            topic.setTpID(rs.getInt("tpID"));
	            topic.setTpTitle(rs.getString("tpTitle"));
	            topic.setTpParent(rs.getInt("tpParent"));
	            topic.setTpStatus(rs.getInt("tpStatus"));
	            topicList.add(topic);
	        }

	        rs.close();
	        pst.close();
	        db.closeConnect();
	    } catch (Exception e) {
	        Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
	    }
	    

	    return topicList;
	}


	    public List<Integer> getAllChildTopics(int topic) throws SQLException {
			
	        List<Integer> topicIDs = new ArrayList<>();
	        ConnectDatabase db = new ConnectDatabase();
	        Connection conn = (Connection) db.connectToDB();
	        
	        	topicIDs.add(topic); 
	        	String query = "SELECT tpID FROM topics WHERE tpParent = ?";
	            try (PreparedStatement ps = conn.prepareStatement(query)) {
	                ps.setInt(1,topic);
	                ResultSet rs = ps.executeQuery();
	                while (rs.next()) {
	                    int childID = rs.getInt("tpID");
	                    topicIDs.addAll(getAllChildTopics(childID)); 
	                
	            }
	        }
	        

	        
	        return topicIDs;
	    }

	  
	public int create(Topic_DTO topic) {
		int res = 0;
		
		try {  
			ConnectDatabase db = new ConnectDatabase();
			Connection conn = db.connectToDB();
			  
			String sql = "INSERT INTO `topics`(`tpTitle`, `tpParent`, `tpStatus`) VALUES (?,?,?)";
			  
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, topic.getTpTitle());  
			pst.setInt(2, topic.getTpParent());
			pst.setInt(3, 1);
			  
			res = pst.executeUpdate();
			
			db.closeConnect();
			pst.close();
		} catch (Exception e) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return res;
	}
	  
	public int update(Topic_DTO topic) {
		int res = 0;
		
		try {
			ConnectDatabase db = new ConnectDatabase();
			Connection conn = db.connectToDB();
			  
			String sql = "UPDATE `topics` SET `tpTitle`=?,`tpParent`=? WHERE `tpID`=?";
			  
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setString(1, topic.getTpTitle());
			pst.setInt(2, topic.getTpParent());
			pst.setInt(3, topic.getTpID());
			  
			res = pst.executeUpdate();
			
			db.closeConnect();
			pst.close();
		} catch (Exception e) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e); 
		}
		
		return res;
	}
	   
	public int delete(int id) {
		int res = 0;
		
		try {
			ConnectDatabase db = new ConnectDatabase();
			Connection conn = db.connectToDB();
			  
			String sql = "UPDATE `topics` SET `tpStatus`=0 WHERE `tpID`=?";
			  
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, id);
			  
			res = pst.executeUpdate();
			
			db.closeConnect();
			pst.close();
		} catch (Exception e) { 
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return res;
	}
}
