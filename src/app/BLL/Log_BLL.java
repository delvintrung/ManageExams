package app.BLL;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import app.DAL.Log_DAL;

public class Log_BLL {
	private Log_DAL dal = new Log_DAL();
	public void insertLog(int userID, String logContent,String logExCode) throws SQLException {
		boolean result = dal.insertLog(userID, logContent, logExCode);
		if(result) {
			JOptionPane.showMessageDialog(null, "Đã lưu bài làm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Lưu bài làm thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
