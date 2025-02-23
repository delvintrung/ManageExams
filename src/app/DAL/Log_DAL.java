package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.database.ConnectDatabase;

public class Log_DAL {
	public boolean insertLog(int userID, String logContent,String logExCode) throws SQLException {
		ConnectDatabase db = new ConnectDatabase();
		Connection conn = db.connectToDB();
		String query = "insert into logs(logContent, logUserID, logExCode, logDate) \r\n"
				+ "value (?, ?, ?, NOW())";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, logContent);
		pst.setInt(2, userID);
		pst.setString(3, logExCode);
		int rowsAffected = pst.executeUpdate();

        
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
	}
}
