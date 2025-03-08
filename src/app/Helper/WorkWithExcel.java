package app.Helper;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import app.BLL.Question_BLL;




public class WorkWithExcel {
	private Question_BLL q_BLL = new Question_BLL();
	
	public boolean readExcel(String file) {
		try (
				FileInputStream fis = new FileInputStream(new File(file));

				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				){
		

			HSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			
        
			if (rowIterator.hasNext()) {
	            rowIterator.next(); 
	        }
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            
            List<String> rowValue = new ArrayList<String>(); 
            
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) 
            {
                Cell cell = cellIterator.next();
                rowValue.add(cell.toString());
            }
            q_BLL.importWithExcel(rowValue);
        }
        
        workbook.close();
        fis.close();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
    }
		return false;
}
	}
