package app.BLL;

import java.util.ArrayList;

import app.DAL.User_DAL;
import app.DTO.User_DTO;
import org.mindrot.jbcrypt.BCrypt;

public class User_BLL {
	public User_DAL userDAL = new User_DAL();
	public User_DTO userDTO = new User_DTO(); 
	
	public static User_DTO getInstance() {
        return new User_DTO();
    }
	
	private String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
    
    public ArrayList<User_DTO> getUsers() {
        return userDAL.getUsers();
    }
    
    public User_DTO getUser(int id) {
    	return userDAL.getUser(id);
    }
    
    public User_DTO getUser(String userName) {
    	return userDAL.getUser(userName);
    }
    
    public ArrayList<User_DTO> search(String search) {
    	return userDAL.search(search);
    }
    
    public boolean create(User_DTO user) {    	
    	if (!user.getUserPassword().equals("")) {
    		String hashedPassword = hashPassword(user.getUserPassword());
    		user.setUserPassword(hashedPassword);
    	}
    	        
        return userDAL.create(user) > 0;
    }
    
    public boolean update(User_DTO existingUser, User_DTO updatedUser) {
    	if (
    			!updatedUser.getUserPassword().equals("") && 
    			!existingUser.getUserPassword().equals(updatedUser.getUserPassword())
    		) 
    	{
    		String hashedPassword = hashPassword(updatedUser.getUserPassword());
    		existingUser.setUserPassword(hashedPassword);
    	}
    	
    	existingUser.setUserEmail(updatedUser.getUserEmail());
    	existingUser.setUserFullName(updatedUser.getUserFullName());
        
        return userDAL.update(existingUser) > 0;
    }
    
    public boolean delete(int id) {
    	return userDAL.delete(id) > 0;
    }
}
