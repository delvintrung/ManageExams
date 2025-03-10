package app.DTO;

public class Result_DTO {
	int rs_num;
	int userID;
	String exCode;
	String[] rs_anwsers;
	float rs_mark;
	String rs_date;
	
	public Result_DTO(int rs_num, int userID, String exCode, String[] rs_anwsers, float rs_mark, String rs_date) {
		super();
		this.rs_num = rs_num;
		this.userID = userID;
		this.exCode = exCode;
		this.rs_anwsers = rs_anwsers;
		this.rs_mark = rs_mark;
		this.rs_date = rs_date;
	}
	
	public Result_DTO( int userID, String exCode, String[] rs_anwsers, float rs_mark) {
		super();
		this.userID = userID;
		this.exCode = exCode;
		this.rs_anwsers = rs_anwsers;
		this.rs_mark = rs_mark;
	}


	public int getRs_num() {
		return rs_num;
	}


	public void setRs_num(int rs_num) {
		this.rs_num = rs_num;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getExCode() {
		return exCode;
	}


	public void setExCode(String exCode) {
		this.exCode = exCode;
	}


	public String[] getRs_anwsers() {
		return rs_anwsers;
	}


	public void setRs_anwsers(String[] rs_anwsers) {
		this.rs_anwsers = rs_anwsers;
	}


	public float getRs_mark() {
		return rs_mark;
	}


	public void setRs_mark(float rs_mark) {
		this.rs_mark = rs_mark;
	}


	public String getRs_date() {
		return rs_date;
	}


	public void setRs_date(String rs_date) {
		this.rs_date = rs_date;
	}
	
	
	
	
}
