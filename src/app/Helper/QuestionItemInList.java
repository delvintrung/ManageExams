package app.Helper;

public class QuestionItemInList {
	private String name;
	private int position;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public QuestionItemInList(String name, int position) {
		this.name = name;
		this.position = position;
	}
	
}
