package app.BLL;

import app.DAL.Result_DAL;
import app.DTO.Result_DTO;

public class Result_BLL {
	Result_DAL r_DAL = new Result_DAL();
	public boolean insertResult(Result_DTO newRes) {
		if(r_DAL.insertResult(newRes) == 1) {
			return true;
		}
		
		return false;
	}
}
