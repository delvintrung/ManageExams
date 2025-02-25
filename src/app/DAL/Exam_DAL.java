package app.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DTO.Exam_DTO;
import app.Helper.ExamData;
import app.Helper.QuestionData;
import app.database.ConnectDatabase;


public class Exam_DAL {
    static ConnectDatabase db;
    
    public List<Exam_DTO> getAllExam() throws SQLException {
    	List<Exam_DTO> result = new ArrayList<Exam_DTO>();
    	db = new ConnectDatabase();
        Connection conn = db.connectToDB();
        String query = "SELECT * FROM exams";
//        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = conn.createStatement().executeQuery(query);
        while (rs.next()) {
            String testCode = rs.getString("testCode");
            String exOrder = rs.getString("exOrder");
            String exCode = rs.getString("exCode");
            String exQues = rs.getString("ex_quesIDs");
            result.add(new Exam_DTO(testCode,exOrder,exCode,exQues));
        }
        if(result.size() > 0 ) {
        	return result;
        }
        return null;
    }

    public ExamData getExamByID(String testCode) throws SQLException {
        db = new ConnectDatabase();
        Connection conn = db.connectToDB();

        String query = "SELECT ex_quesIDs FROM exams WHERE testCode = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, testCode);
        ResultSet rs = pst.executeQuery();

        String questionsStr = null;
        if (rs.next()) {
            questionsStr = rs.getString("ex_quesIDs");
        }

        if (questionsStr == null || questionsStr.isEmpty()) {
            return new ExamData(new String[]{}, new String[][]{}, new String[]{});
        }

        String[] listIDQues = questionsStr.split(",");

        List<String> questionsList = new ArrayList<>();
        List<String[]> optionsList = new ArrayList<>();
        List<String> correctAnswersList = new ArrayList<>();

        for (int i = 0; i < listIDQues.length; i++) {
            int quesID = Integer.parseInt(listIDQues[i]);
            QuestionData questionData = getQuestionWithAnswers(conn, quesID);
            if (questionData != null) {
                questionsList.add("Câu " + (i + 1) + ": " + questionData.quesText);
                optionsList.add(questionData.answers);
                correctAnswersList.add(questionData.correctAnswer);
            }
        }

        rs.close();
        pst.close();
        db.closeConnect();

        return new ExamData(
                questionsList.toArray(new String[0]),
                optionsList.toArray(new String[0][]),
                correctAnswersList.toArray(new String[0])
        );
    }

    private QuestionData getQuestionWithAnswers(Connection conn, int quesID) throws SQLException {
        String query = "SELECT * FROM questions WHERE qID = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, quesID);
        ResultSet rs = pst.executeQuery();

        QuestionData questionData = null;
        if (rs.next()) {
            String quesContent = rs.getString("qContent");
            String[] answers = getAnswers(conn, quesID);
            String correctAnswer = getCorrectAnswer(conn, quesID);

            questionData = new QuestionData(quesContent, answers, correctAnswer);
        }

        rs.close();
        pst.close();
        return questionData;
    }

    private String[] getAnswers(Connection conn, int quesID) throws SQLException {
        List<String> answers = new ArrayList<>();
        String query = "SELECT awContent FROM answers WHERE qID = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, quesID);
        ResultSet rs = pst.executeQuery();
        int i = 0;
        while (rs.next()) {
        	if(i == 0) {
        		answers.add("A. " + rs.getString("awContent"));
        	}
        	if(i == 1) {
        		answers.add("B. " + rs.getString("awContent"));
        	}
        	if(i == 2) {
        		answers.add("C. " + rs.getString("awContent"));
        	}
        	if(i == 3) {
        		answers.add("D. " + rs.getString("awContent"));
        	}
        	if(i == 4) {
        		answers.add("E. " + rs.getString("awContent"));
        	}
            i++;
        }

        rs.close();
        pst.close();
        return answers.toArray(new String[0]);
    }

    private String getCorrectAnswer(Connection conn, int quesID) throws SQLException {
        String query = "SELECT awContent FROM answers WHERE qID = ? AND isRight = 1";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, quesID);
        ResultSet rs = pst.executeQuery();

        String correctAnswer = null;
        if (rs.next()) {
            correctAnswer = rs.getString("awContent");
        }

        rs.close();
        pst.close();
        return correctAnswer;
    }
    
    
    
}
