/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.DAL;

import app.DTO.Dashboard_DTO;
import app.database.ConnectDatabase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class Dashboard_DAL {
	public ArrayList<Dashboard_DTO> getDashboard() {
		try {
			ConnectDatabase db = new ConnectDatabase();
			Connection conn = db.connectToDB();
			
			ArrayList<Dashboard_DTO> result = new ArrayList<Dashboard_DTO>();
			
			String query = "SELECT\n" +
"    testcode AS \"Bài thi\",\n" +
"    COUNT(userID) AS \"SL dự thi\",\n" +
"    SUM(CASE WHEN rs_mark >= 5 THEN 1 ELSE 0 END) AS \"SL đạt\",\n" +
"    SUM(CASE WHEN rs_mark < 5 THEN 1 ELSE 0 END) AS \"SL không đạt\"\n" +
"FROM exams\n" +
"LEFT JOIN result ON result.exCode = exams.exCode\n" +
"GROUP BY exams.testcode;";
			
			ResultSet rs = conn.createStatement().executeQuery(query);
			
			while (rs.next()) {
				int SLdat = rs.getInt("SL Đạt");
				String testcode = rs.getString("Bài thi");
				int SLduthi = rs.getInt("SL dự thi");
				int SLkodat = rs.getInt("SL không đạt");
				result.add(new Dashboard_DTO(testcode, SLduthi, SLkodat,SLdat));
			}
			
			db.closeConnect();
			
			return result.size() > 0 ? result : null;
		} catch (Exception e) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
        
        	public ArrayList<Dashboard_DTO> getDashboardfilter(String testcode,Date from,Date to) {
		try {
			ConnectDatabase db = new ConnectDatabase();
			Connection conn = db.connectToDB();
			
			ArrayList<Dashboard_DTO> result = new ArrayList<Dashboard_DTO>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String dateStringfrom = dateFormat.format(from);
                        String dateStringto = dateFormat.format(to);
			String query = "SELECT\n" +
"    testcode AS \"Bài thi\",\n" +
"    COUNT(userID) AS \"SL dự thi\",\n" +
"    SUM(CASE WHEN rs_mark >= 5 THEN 1 ELSE 0 END) AS \"SL đạt\",\n" +
"    SUM(CASE WHEN rs_mark < 5 THEN 1 ELSE 0 END) AS \"SL không đạt\"\n" +
"FROM exams\n" +
"LEFT JOIN result ON result.exCode = exams.exCode\n" +
"WHERE result.rs_date BETWEEN '"+dateStringfrom+"' AND '"+dateStringto+"' AND testcode LIKE '%"+testcode+"%'\n" +
"GROUP BY exams.testcode;";
			
			ResultSet rs = conn.createStatement().executeQuery(query);
			
			while (rs.next()) {
				int SLdat = rs.getInt("SL Đạt");
				String testcodee = rs.getString("Bài thi");
				int SLduthi = rs.getInt("SL dự thi");
				int SLkodat = rs.getInt("SL không đạt");
				result.add(new Dashboard_DTO(testcodee, SLduthi, SLkodat,SLdat));
			}
			
			db.closeConnect();
			
			return result.size() > 0 ? result : null;
		} catch (Exception e) {
			Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
}
