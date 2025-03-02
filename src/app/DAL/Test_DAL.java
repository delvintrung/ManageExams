package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.DTO.Question_DTO;
import app.DTO.Test_DTO;
import app.database.ConnectDatabase;

public class Test_DAL {
    private ConnectDatabase db; 
    
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

    public boolean saveTestAndExams(String testCode, String testTitle, int testTime, int tpID, 
                         int numEasy, int numMedium, int numDiff, int testLimit, 
                         String testDate, int testStatus, List<Integer> questionIds) throws SQLException {
        db = new ConnectDatabase();
        Connection conn = db.connectToDB();

        try {
            String queryTest = "INSERT INTO test (testCode, testTilte, testTime, num_easy, num_medium, num_diff, testLimit, testDate, testStatus) " +
                               "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement psTest = conn.prepareStatement(queryTest);
            psTest.setString(1, testCode);
            psTest.setString(2, testTitle);
            psTest.setInt(3, testTime);
            psTest.setInt(5, numEasy);
            psTest.setInt(6, numMedium);
            psTest.setInt(7, numDiff);
            psTest.setInt(8, testLimit);
            psTest.setString(9, testDate);
            psTest.setInt(10, testStatus);

            psTest.executeUpdate();

            
            for (char exOrder = 'A'; exOrder <= 'J'; exOrder++) {
                String exCode = testCode + exOrder; 

                List<Integer> shuffledQuestions = new java.util.ArrayList<>(questionIds);
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
