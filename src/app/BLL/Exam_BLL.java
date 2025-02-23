package app.BLL;

import java.sql.SQLException;
import java.util.List;

import app.DAL.Exam_DAL;
import app.DTO.Exam_DTO;

public class Exam_BLL {
	public String[][] getAllExamForTable() throws SQLException {
		Exam_DAL dal = new Exam_DAL();
		List<Exam_DTO> all = dal.getAllExam();
		System.out.println("Size:"+ all.size());
		String[][] result = new String[all.size()][];;
		
		int index = 0;
		for(Exam_DTO item : all) {
			int num = item.getEx_quesIDs().split(",").length;
			
			result[index] = new String[]{
			        item.getTestCode(), 
			        item.getExOrder(),
			        item.getExCode(),
			        String.valueOf(num),
			        "Bình thường"
			    };
			    index++;
		}
		return result;
		
	}
}
