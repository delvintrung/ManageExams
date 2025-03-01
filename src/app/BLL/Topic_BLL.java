package app.BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DAL.Topic_DAL;
import app.DTO.Topic_DTO;
import app.Helper.ComboItem;

public class Topic_BLL {
	private Topic_DAL tpDal = new Topic_DAL();
	
	public ArrayList<Topic_DTO> getTopics() {
		return tpDal.getTopics();
	}
	
	public List<Topic_DTO> getParentTopic() throws SQLException {
		if(tpDal.getParentTopic() != null) {
			return tpDal.getParentTopic();
		}
		return null;

	} 

	public List<ComboItem> getChilTopic() throws SQLException {
		List<Topic_DTO> childs = tpDal.getChildTopic(); 
		if(childs != null) {
			List<ComboItem> items = new ArrayList<ComboItem>();
			for(Topic_DTO child: childs ) {
				ComboItem item = new ComboItem(child.getTpTitle(), child.getTpID());
				items.add(item);
			}
			return items;
		}
		return null;
	} 
	
	public ArrayList<Topic_DTO> search(String search) {
		return tpDal.search(search);
	}
	
	public boolean create(Topic_DTO topic) {
		return tpDal.create(topic) > 0;
	}
	
	public boolean update(Topic_DTO topic) {
		return tpDal.update(topic) > 0;
	}
	
	public boolean delete(int id) {
		return tpDal.delete(id) > 0;
	}
}
