package app.DTO;

public class Test_DTO {
	int testID;
	String testCode;
	String testTitle;
	int testTime;
	int tpID;
	int num_easy;
	int num_medium;
	int num_diff;
	int testLimit;
	String testDate;
	int testStatus;
	
	public Test_DTO() {
		super();
		this.testID = 0;
		this.testCode = "";
		this.testTitle = "";
		this.testTime = 0;
		this.tpID = 0;
		this.num_easy = 0;
		this.num_medium = 0;
		this.num_diff = 0;
		this.testLimit = 0;
		this.testDate = "";
		this.testStatus = 0;
	}
	
	public Test_DTO(int testID, String testCode, String testTitle, int testTime, int tpID, int num_easy, int num_medium,
			int num_diff, int testLimit, String testDate, int testStatus) {
		super();
		this.testID = testID;
		this.testCode = testCode;
		this.testTitle = testTitle;
		this.testTime = testTime;
		this.tpID = tpID;
		this.num_easy = num_easy;
		this.num_medium = num_medium;
		this.num_diff = num_diff;
		this.testLimit = testLimit;
		this.testDate = testDate;
		this.testStatus = testStatus;
	}
	
	
	
	
}
