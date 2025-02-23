package app.DTO;

public class Log_DTO {
	String logContent;
	int logUserID;
	String logExCode;
	String logDate;
	
	public Log_DTO() {
		super();
		this.logContent = "";
		this.logUserID = 0;
		this.logExCode = "";
		this.logDate = "";
	}
	
	public Log_DTO(String logContent, int logUserID, String logExCode, String logDate) {
		super();
		this.logContent = logContent;
		this.logUserID = logUserID;
		this.logExCode = logExCode;
		this.logDate = logDate;
	}
	
	
}
