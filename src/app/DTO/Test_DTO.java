package app.DTO;

public class Test_DTO {
	int testID;
	String testCode;
	String testTitle;
	int testTime;
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
		this.num_easy = 0;
		this.num_medium = 0;
		this.num_diff = 0;
		this.testLimit = 0;
		this.testDate = "";
		this.testStatus = 0;
	}
	
	public Test_DTO(int testID, String testCode, String testTitle, int testTime,  int num_easy, int num_medium,
			int num_diff, int testLimit, String testDate, int testStatus) {
		super();
		this.testID = testID;
		this.testCode = testCode;
		this.testTitle = testTitle;
		this.testTime = testTime;
		this.num_easy = num_easy;
		this.num_medium = num_medium;
		this.num_diff = num_diff;
		this.testLimit = testLimit;
		this.testDate = testDate;
		this.testStatus = testStatus;
	}

	public Test_DTO(String testCode, String testTitle, int testTime, int num_easy, int num_medium,
			int num_diff, int testLimit, String testDate, int testStatus) {
		super();
		this.testCode = testCode;
		this.testTitle = testTitle;
		this.testTime = testTime;
		this.num_easy = num_easy;
		this.num_medium = num_medium;
		this.num_diff = num_diff;
		this.testLimit = testLimit;
		this.testDate = testDate;
		this.testStatus = testStatus;
	}

	public int getTestID() {
		return testID;
	}

	public void setTestID(int testID) {
		this.testID = testID;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getTestTitle() {
		return testTitle;
	}

	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}

	public int getTestTime() {
		return testTime;
	}

	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}


	public int getNum_easy() {
		return num_easy;
	}

	public void setNum_easy(int num_easy) {
		this.num_easy = num_easy;
	}

	public int getNum_medium() {
		return num_medium;
	}

	public void setNum_medium(int num_medium) {
		this.num_medium = num_medium;
	}

	public int getNum_diff() {
		return num_diff;
	}

	public void setNum_diff(int num_diff) {
		this.num_diff = num_diff;
	}

	public int getTestLimit() {
		return testLimit;
	}

	public void setTestLimit(int testLimit) {
		this.testLimit = testLimit;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public int getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(int testStatus) {
		this.testStatus = testStatus;
	}
	
	
	
	
}
