package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.DTO.Test_DTO;
import app.database.ConnectDatabase;

public class Test_DAL {
    private ConnectDatabase db; 
    
    public List<Test_DTO> getAllTest() throws SQLException {
    	List<Test_DTO> tests = new ArrayList<Test_DTO>();
    	ConnectDatabase db = new ConnectDatabase();
        Connection conn = (Connection) db.connectToDB();
        String sql = "SELECT * from test where testStatus = 1";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while(rs.next()) {
        	
        	int testID = rs.getInt("testID");
        	String testCode = rs.getString("testCode");
        	String testTitle = rs.getString("testTitle");
        	int testTime = rs.getInt("testTime");
        	int num_easy = rs.getInt("num_easy");
        	int num_medium = rs.getInt("num_medium");
        	int num_diff = rs.getInt("num_diff");
        	int testLimit = rs.getInt("testLimit");
        	String testDate = rs.getString("testDate"); 
        	int testStatus = rs.getInt("testStatus");
        	Test_DTO newTest = new Test_DTO(testID, testCode,testTitle, testTime, num_easy, num_medium, num_diff,testLimit, testDate, testStatus);
        	tests.add(newTest);
        }
        return tests;
    }
    
    public int getTestIDByTestCode(String testCode) {
    	int result = 0;
    	try {
    		ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            String sql = "SELECT testID from test where testCode = ?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, testCode);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                result = rs.getInt("testID");
            }
            return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
    }
    
    
    public void insertRelationTestAndTopic(int testID, List<Integer> topics) throws SQLException {
    	ConnectDatabase db = new ConnectDatabase();
        Connection conn = (Connection) db.connectToDB();
    	for(int topic: topics) {
    		String query = "insert into test_topic(testID,tpID) value (?, ?)";
    		try {
				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
				pst.setInt(1, testID);
				pst.setInt(2, topic);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	db.closeConnect();
    }
    
    public void insertRelationTestAndQuestion(int testID, List<Integer> questions) throws SQLException {
    	ConnectDatabase db = new ConnectDatabase();
        Connection conn = (Connection) db.connectToDB();
    	for(int topic: questions) {
    		String query = "insert into test_questions(testID,quesID) value (?, ?)";
    		try {
				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);
				pst.setInt(1, testID);
				pst.setInt(2, topic);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	db.closeConnect();
    }
    
    public boolean insertTest(Test_DTO newTest) {
    	boolean result = false;
        try {
        	ConnectDatabase db = new ConnectDatabase();
            Connection conn = (Connection) db.connectToDB();
            String sql = "INSERT INTO `test`(`testCode`, `testTitle`, `testTime`, `num_easy`, `num_medium`, `num_diff`, `testLimit`, `testDate`, `testStatus`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, newTest.getTestCode());
            pst.setString(2, newTest.getTestTitle());
            pst.setInt(3, newTest.getTestTime());
            pst.setInt(4, newTest.getNum_easy());
            pst.setInt(5, newTest.getNum_medium());
            pst.setInt(6, newTest.getNum_diff());
            pst.setInt(7, newTest.getTestLimit());
            pst.setString(8, newTest.getTestDate());
            pst.setInt(9, newTest.getTestStatus());
            pst.execute();
            result = true;
            db.closeConnect();
        } catch (SQLException ex) {
            Logger.getLogger(Question_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean GenarateExams(String testCode, List<Integer> questionIds) throws SQLException {
    	System.out.println(questionIds);
        db = new ConnectDatabase();
        Connection conn = db.connectToDB();
        try {
	        for (char exOrder = 'A'; exOrder <= 'J'; exOrder++) {
	            String exCode = testCode + exOrder; 
	
	            List<Integer> shuffledQuestions = new java.util.ArrayList<>(questionIds);
	            System.out.println(shuffledQuestions);
	            java.util.Collections.shuffle(shuffledQuestions);
	
	            String questionsStr = shuffledQuestions.toString().replace("[", "").replace("]", "");
	
	            String queryExam = "INSERT INTO exams (testCode, exOrder, exCode, ex_quesIDs) VALUES (?, ?, ?, ?)";
	            PreparedStatement psExam = conn.prepareStatement(queryExam);
	            psExam.setString(1, testCode);
	            psExam.setString(2, String.valueOf(exOrder));
	            psExam.setString(3, exCode);
	            psExam.setString(4, questionsStr);
	
	            psExam.executeUpdate();
	            return true;
	        }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
		return false;
    }

    
	
}
