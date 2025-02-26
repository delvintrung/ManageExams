package app.Helper;

public class ComboItem {
	
	    private String value;
	    private int key;

	    public ComboItem(String value, int key) {
	        this.key = key;
	        this.value = value;
	    }

	    public int getKey() {
	        return key;
	    }

	    @Override
	    public String toString() {
	        return value; 
	    }
	
}
