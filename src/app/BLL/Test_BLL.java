package app.BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DAL.Test_DAL;
import app.DTO.Question_DTO;


public class Test_BLL {
	Test_DAL test_DAL = new Test_DAL();
	public boolean saveTest(List<Question_DTO> quesList, String testCode, String testTitle, int time, int limit) {
		int numEasy = 0, numMedium = 0, numDiff = 0;
        for (Question_DTO question : quesList) {
            switch (question.getqLevel()) {
                case "easy": numEasy++; break;
                case "medium": numMedium++; break;
                case "hard": numDiff++; break;
            }
        }
        
        List<Integer> questionIds = new ArrayList<Integer>();
        for(Question_DTO question : quesList ) {
        	questionIds.add(question.getqID());
        }
        
        try {
			if(test_DAL.saveTestAndExams(testCode, testTitle,time,limit, numEasy, numMedium, numDiff, 1, "30/02/2025", 1, questionIds)) {
				return true;
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
