package app.DTO;

import java.util.ArrayList;
import java.util.List;

public class Question_DTO {
	private int qID;
	private String qContent;
	private String qPicture;
	private int qTopicID;
	private String qLevel;
	private int qStatus;
	private List<Answer_DTO> answers;
	 
	 public Question_DTO() {
			super();
			this.qContent = "";
			this.qPicture = "";
			this.qTopicID = 0;
			this.qLevel = "";
			this.qStatus = 0;
			this.answers = new ArrayList<>();
		}
	 
	 public Question_DTO(String qContent, String qPicture, int qTopicID, String qLevel, int qStatus) {
			super();
			this.qContent = qContent;
			this.qPicture = qPicture;
			this.qTopicID = qTopicID;
			this.qLevel = qLevel;
			this.qStatus = qStatus;
			this.answers = new ArrayList<>();
		}
	 
	public Question_DTO(int qID, String qContent, String qPicture, int qTopicID, String qLevel, int qStatus) {
		super();
		this.qID = qID;
		this.qContent = qContent;
		this.qPicture = qPicture;
		this.qTopicID = qTopicID;
		this.qLevel = qLevel;
		this.qStatus = qStatus;
		this.answers = new ArrayList<>();
	}

	public int getqID() {
		return qID;
	}

	public void setqID(int qID) {
		this.qID = qID;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public String getqPicture() {
		return qPicture;
	}

	public void setqPicture(String qPicture) {
		this.qPicture = qPicture;
	}

	public int getqTopicID() {
		return qTopicID;
	}

	public void setqTopicID(int qTopicID) {
		this.qTopicID = qTopicID;
	}

	public String getqLevel() {
		return qLevel;
	}

	public void setqLevel(String qLevel) {
		this.qLevel = qLevel;
	}

	public int getqStatus() {
		return qStatus;
	}

	public void setqStatus(int qStatus) {
		this.qStatus = qStatus;
	}
	
	public List<Answer_DTO> getAnswers() { return answers; }
    public void setAnswers(List<Answer_DTO> answers) { this.answers = answers; }
    
    public Answer_DTO getCorrectAnswer() {
        for (Answer_DTO answer : answers) {
            if (answer.getIsRight() == 1) {
                return answer;
            }
        }
        return null;
    }
	 
}
