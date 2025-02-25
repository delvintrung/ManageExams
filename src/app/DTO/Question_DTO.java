package app.DTO;

public class Question_DTO {
	int qID;
	 String qContent;
	 String qPicture;
	 int qTopicID;
	 String qLevel;
	 int qStatus;
	 
	 public Question_DTO() {
			super();
			this.qContent = "";
			this.qPicture = "";
			this.qTopicID = 0;
			this.qLevel = "";
			this.qStatus = 0;
		}
	 
	 public Question_DTO(String qContent, String qPicture, int qTopicID, String qLevel, int qStatus) {
			super();
			this.qContent = qContent;
			this.qPicture = qPicture;
			this.qTopicID = qTopicID;
			this.qLevel = qLevel;
			this.qStatus = qStatus;
		}
	 
	public Question_DTO(int qID, String qContent, String qPicture, int qTopicID, String qLevel, int qStatus) {
		super();
		this.qID = qID;
		this.qContent = qContent;
		this.qPicture = qPicture;
		this.qTopicID = qTopicID;
		this.qLevel = qLevel;
		this.qStatus = qStatus;
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
	 
	 
}
