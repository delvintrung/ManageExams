package app.DTO;

public class Topic_DTO {
	String tpTitle;
	int tpParent;
	int tpStatus;
	
	public Topic_DTO() {
		super();
		this.tpTitle = "";
		this.tpParent = 0;
		this.tpStatus = 0;
	}
	
	public Topic_DTO(String tpTitle, int tpParent, int tpStatus) {
		super();
		this.tpTitle = tpTitle;
		this.tpParent = tpParent;
		this.tpStatus = tpStatus;
	}
	
	
	
}
