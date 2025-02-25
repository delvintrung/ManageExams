package app.BLL;

import java.sql.SQLException;
import java.util.List;

import app.DAL.Topic_DAL;
import app.DTO.Topic_DTO;

public class Topic_BLL {
	Topic_DAL tpDal = new Topic_DAL();
	public List<Topic_DTO> getParentTopic() throws SQLException {
		if(tpDal.getParentTopic() != null) {
			return tpDal.getParentTopic();
		}
		return null;
	} 
}
