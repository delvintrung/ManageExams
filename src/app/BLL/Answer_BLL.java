package app.BLL;

import java.util.ArrayList;

import app.DAL.Answer_DAL;
import app.DTO.Answer_DTO;

public class Answer_BLL {
	private Answer_DAL aDAL;
	
	public Answer_BLL() {
		aDAL = new Answer_DAL();
	}
	
	public ArrayList<Answer_DTO> getQuestionAnswers(int questionId) {
		return aDAL.getQuestionAnswers(questionId);
	}
	
	public boolean insertRowText(int qID, String content, int isRight) {
		boolean result = false;
		if(aDAL.insertAnswerText(qID, content, isRight) == 1) {
			result = true;
		};
		return result;
	}
	
	public boolean insertRowImage(int qID, String content, int isRight) {
		boolean result = false;
		if(aDAL.insertAnswerImage(qID, content, isRight) == 1) {
			result = true;
		};
		return result;
	}
}
