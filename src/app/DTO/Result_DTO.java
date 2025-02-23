package app.DTO;

public class Result_DTO {
	int rs_num;
	int userID;
	String exCode;
	String rs_anwsers;
	float rs_mark;
	String rs_date;
	
	public Result_DTO() {
		super();
		this.rs_num = 0;
		this.userID = 0;
		this.exCode = "";
		this.rs_anwsers = "";
		this.rs_mark = 0;
		this.rs_date = "";
	}
	
	
	public Result_DTO(int rs_num, int userID, String exCode, String rs_anwsers, float rs_mark, String rs_date) {
		super();
		this.rs_num = rs_num;
		this.userID = userID;
		this.exCode = exCode;
		this.rs_anwsers = rs_anwsers;
		this.rs_mark = rs_mark;
		this.rs_date = rs_date;
	}
	
	
	
}
