package app.DTO;

public class Answer_DTO {
	int awID;
	int qID;
	String awContent;
	String awPictures;
	int isRight;
	int awStatus;
	
	public Answer_DTO() {
		super();
		this.qID = 0;
		this.awContent = "";
		this.awPictures = "";
		this.isRight = 0;
		this.awStatus = 0;
	}
	
	public Answer_DTO(int awID,int qID, String awContent, String awPictures, int isRight, int awStatus) {
		
		super();
		this.awID = awID;
		this.qID = qID;
		this.awContent = awContent;
		this.awPictures = awPictures;
		this.isRight = isRight;
		this.awStatus = awStatus;
	}
	
	
	
}
