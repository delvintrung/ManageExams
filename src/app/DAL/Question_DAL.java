package app.DAL;

import java.sql.Connection;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String sql = "UPDATE `questions` SET `qStatus`=0 WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, id);
            result = pst.executeUpdate();
            db.closeConnect();;
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
}
