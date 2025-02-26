package app.DTO;

public class Topic_DTO {
	int tpID;
	String tpTitle;
	int tpParent;
	int tpStatus;
	
	public Topic_DTO() {
		super();
		this.tpTitle = "";
		this.tpParent = 0;
		this.tpStatus = 0;
	}
	
	public Topic_DTO(int tpID, String tpTitle, int tpParent, int tpStatus) {
		super();
		this.tpID = tpID;
		this.tpTitle = tpTitle;
		this.tpParent = tpParent;
		this.tpStatus = tpStatus;
	}

	public int getTpID() {
		return tpID;
	}

	public void setTpID(int tpID) {
		this.tpID = tpID;
	}

	public String getTpTitle() {
		return tpTitle;
	}

	public void setTpTitle(String tpTitle) {
		this.tpTitle = tpTitle;
	}

	public int getTpParent() {
		return tpParent;
	}

	public void setTpParent(int tpParent) {
		this.tpParent = tpParent;
	}

	public int getTpStatus() {
		return tpStatus;
	}

	public void setTpStatus(int tpStatus) {
		this.tpStatus = tpStatus;
	}

	@Override
	public String toString() {
	    return this.getTpTitle();
	}

}
