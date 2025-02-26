package app.BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DAL.Topic_DAL;
import app.DTO.Topic_DTO;
import app.Helper.ComboItem;

public class Topic_BLL {
	Topic_DAL tpDal = new Topic_DAL();
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
}
