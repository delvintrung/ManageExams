package app.Helper;
import java.util.regex.Pattern;

public class Validator {
	public static boolean isEmpty(String input) {
        if(input == null)
            return true;
        return input.equals("");
    }
    
    public static boolean isInteger(String num) {
        if (num == null) return false;
        try {
            if(Integer.valueOf(num) < 0)
                return false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isFloat(String num) {
        if (num == null) return false;
        try {
            if(Float.valueOf(num) < 0)
                return false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isPhoneNumber(String phoneNum) {
        if(phoneNum == null) {
            return false;
        }
        String phoneNumRegex = "^(?:[0-9]●?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(phoneNumRegex);
        return pattern.matcher(phoneNum).matches();
    }
    
    public static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
