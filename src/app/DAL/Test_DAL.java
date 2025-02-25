package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import app.database.ConnectDatabase;

public class Test_DAL {
    private ConnectDatabase db; 

    public boolean saveTestAndExams(String testCode, String testTitle, int testTime, int tpID, 
                         int numEasy, int numMedium, int numDiff, int testLimit, 
                         String testDate, int testStatus, List<Integer> questionIds) throws SQLException {
        db = new ConnectDatabase();
        Connection conn = db.connectToDB();

        try {
            String queryTest = "INSERT INTO test (testCode, testTilte, testTime, tpID, num_easy, num_medium, num_diff, testLimit, testDate, testStatus) " +
                               "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement psTest = conn.prepareStatement(queryTest);
            psTest.setString(1, testCode);
            psTest.setString(2, testTitle);
            psTest.setInt(3, testTime);
            psTest.setInt(4, tpID);
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
