package app.DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DTO.Topic_DTO;
import app.database.ConnectDatabase;

public class Topic_DAL {
	  ConnectDatabase db;
	    
	    public List<Topic_DTO> getParentTopic() throws SQLException {
	    	List<Topic_DTO> result = new ArrayList<Topic_DTO>();
	    	db = new ConnectDatabase();
	        Connection conn = db.connectToDB();
	        String query = "SELECT * FROM topics where tpParent = 0";
//	        PreparedStatement pst = conn.prepareStatement(query);
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
}
