package app.BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DAL.Question_DAL;
import app.DTO.Question_DTO;
import app.DTO.Topic_DTO;
import app.Helper.ComboItem;

public class Question_BLL {
	public Question_DAL q_DAL = new Question_DAL();
	public ArrayList<Question_DTO> questionList = new ArrayList<Question_DTO>();
	public ArrayList<Question_DTO> getAllQuestion() {
		questionList = q_DAL.selectAll();
		return questionList;
	}
	public int getIndexById(int id) {
        for(int i=0; i<this.questionList.size(); i++) {
            if(this.questionList.get(i).getqID() == id)
                return i;
        }
        return -1;
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
        	questionList.set(getIndexById(q.getqID()), q);
            return true;
        }
        return false;
	}
	
 
}
