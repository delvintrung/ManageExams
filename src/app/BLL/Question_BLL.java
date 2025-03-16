package app.BLL;


import java.security.spec.DSAPublicKeySpec;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.harmony.unpack200.bytecode.forms.ThisFieldRefForm;

import app.DAL.Answer_DAL;
import app.DAL.Question_DAL;
import app.DTO.Answer_DTO;
import app.DTO.Question_DTO;

public class Question_BLL {
	private Question_DAL q_DAL;
	private Answer_DAL a_DAL= new Answer_DAL();
	private ArrayList<Question_DTO> questionList;
	
	public Question_BLL() {
		q_DAL = new Question_DAL();
		questionList = new ArrayList<Question_DTO>();
	}
	
	public Question_DTO getQuestion(int id) {
		return q_DAL.getQuestion(id);
	}

	public ArrayList<Question_DTO> getAllQuestion() {
		questionList = q_DAL.selectAll();
		return questionList;
	}
	
	public int getIndexById(int id) {
        for(int i=0; i<=this.questionList.size(); i++) {
            if(this.questionList.get(i).getqID() == id)
                return i;
        }
        return -1;
    }
	
	public int getAutoIncrement() {
		int result = 0;
		result = q_DAL.getAutoIncrement();
		return result;
	}
	
	public ArrayList<Question_DTO> search(String text, String type) {
        ArrayList<Question_DTO> result = new ArrayList<Question_DTO>();
        text = text.toLowerCase();
        switch (type) {
            case "Tất cả":
                for (Question_DTO i : questionList) {
                    if (Integer.toString(i.getqID()).toLowerCase().contains(text) || i.getqContent().toLowerCase().contains(text) || Float.toString(i.getqStatus()).toLowerCase().contains(text) || i.getqLevel().toLowerCase().contains(text) || Integer.toString(i.getqTopicID()).contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Câu hỏi":
                for (Question_DTO i : questionList) {
                    if (i.getqContent().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Topic":
                for (Question_DTO i : questionList) {
                    if (Integer.toString(i.getqTopicID()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Mức độ":
                for (Question_DTO i : questionList) {
                    if (i.getqLevel().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case "Trạng thái":
                for (Question_DTO i : questionList) {
                    if (Integer.toString(i.getqStatus()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            
            default:
                throw new AssertionError();
        }
        return result;
    }
	
	public boolean delete(Question_DTO q) {
        if (q_DAL.delete(q.getqID()) != 0) {
        	questionList.remove(q);
            return true;
        }
        return false;
    }
	
	public boolean add(Question_DTO q) {
        if (q_DAL.insert(q) != 0) {
        	questionList.add(q);
            return true;
        }
        return false;
    }
	
	public boolean edit(Question_DTO q) {
		if (q_DAL.update(q) != 0) {
			getAllQuestion();
//        	questionList.set(getIndexById(q.getqID()), q);
            return true;
        }
        return false;
	}
	
	public void importWithExcel(List<String> row) {
		List<Answer_DTO> listAnswer = new ArrayList<Answer_DTO>(); 
		Question_DTO newQues = new Question_DTO(row.get(0), row.get(1),(int)Double.parseDouble(row.get(2)),row.get(3), 1);
		
		for(int i = 4; i < row.size(); i+=2 ) {
			if(!row.get(i).isEmpty()) {
				Answer_DTO newAnswer = new Answer_DTO(row.get(i), row.get(i+1) == "TRUE" ? 1 : 0, 1); 
				listAnswer.add(newAnswer);
			}
		}
		
		if(newQues != null && listAnswer.size() > 0) {
			q_DAL.insert(newQues);
			int newID = q_DAL.getAutoIncrement();
			for(Answer_DTO ans : listAnswer) {
				a_DAL.insertAnswerText(newID, ans.getAwContent(), ans.getIsRight());
			}
		}
	}
	
	
	public List<Integer> getQuesOfTestByTestId(int testID) {
		
		try {
			return q_DAL.getQuesOfTestByTestId(testID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public boolean saveImageToDatabase(String imagePath, int idQues) {
		if(imagePath.isBlank() || idQues < 0) {
			return false;
		} else {
			q_DAL.saveImageToDatabase(imagePath, idQues);
		}
		return true;
		
	}
	

 
}
