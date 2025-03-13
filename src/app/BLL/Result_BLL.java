package app.BLL;


import java.util.ArrayList;


import app.DAL.Result_DAL;
import app.DTO.Result_DTO;

public class Result_BLL {

	private Result_DAL resultDAL;
	
	public Result_BLL() {
		resultDAL = new Result_DAL();
	}
	
	public ArrayList<Result_DTO> getUserResults(int userId) {
		return resultDAL.getUserResults(userId);
	}
		
	public boolean insertResult(Result_DTO newRes) {
		if(resultDAL.insertResult(newRes) == 1) {
			return true;
		}
		
		return false;

	}
}
