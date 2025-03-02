package app.BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DAL.Question_DAL;
import app.DAL.Test_DAL;
import app.DAL.Topic_DAL;
import app.DTO.Test_DTO;

public class Test_BLL {
	Test_DAL test_DAL = new Test_DAL();
	Topic_DAL tp_DAL = new Topic_DAL();
	Question_DAL q_DAL = new Question_DAL();
	public boolean saveTest(String testCode, String testTitle, int time,List<Integer> tpID, int limit, int numEasy, int numMedium, int numDiff, String date) {
		Test_DTO newTest = new Test_DTO(testCode, testTitle,time, numEasy, numMedium, numDiff,limit, date,1); 
		List<Integer> allChildTopic = new  ArrayList<Integer>();
        if(test_DAL.insertTest(newTest)) {
        	for(int tp: tpID) {
        		try {
					List<Integer> result = tp_DAL.getAllChildTopics(tp);
					for(int item: result) {
						allChildTopic.add(item);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
        	}
        	int idTest = test_DAL.getTestIDByTestCode(testCode);
        	try {
				test_DAL.insertRelationTestAndTopic(idTest, tpID);
				List<Integer> questionsIntegers = q_DAL.getRandomQuestions(allChildTopic, numEasy, numMedium, numDiff);
				test_DAL.insertRelationTestAndQuestion(idTest, questionsIntegers);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return true;
        }
		return false;
	}
}
