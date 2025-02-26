package app.BLL;

import app.DAL.Answer_DAL;

public class Answer_BLL {
	Answer_DAL aDAL = new Answer_DAL();
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
