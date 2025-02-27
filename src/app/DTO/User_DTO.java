package app.DTO;

public class User_DTO {
	private int userID;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userFullName;
	private int isAdmin;
	private int uStatus;
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getuStatus() {
		return uStatus;
	}

	public void setuStatus(int uStatus) {
		this.uStatus = uStatus;
	}

	public User_DTO() {
		super();
		this.userID = 0;
		this.userName = "";
		this.userEmail = "";
		this.userPassword = "";
		this.userFullName = "";
		this.isAdmin = 0;
		this.uStatus = 1;
	}

	public User_DTO(int userID, String userName, String userEmail, String userPassword, String userFullName, int isAdmin) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userFullName = userFullName;
		this.isAdmin = isAdmin;
	}
	
	public User_DTO(String userName, String userEmail, String userPassword, String userFullName, int isAdmin) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userFullName = userFullName;
		this.isAdmin = isAdmin;
	}
}
