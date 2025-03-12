package app.Helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	public void writtingExcel(String url) {
		
	    XSSFWorkbook workbook = new XSSFWorkbook();

	    XSSFSheet sheet = workbook.createSheet("Employee Data");

	    Map<String, Object[]> data = new TreeMap<String, Object[]>();
	    data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
	    data.put("2", new Object[]{1, "Amit", "Shukla"});
	    data.put("3", new Object[]{2, "Lokesh", "Gupta"});
	    data.put("4", new Object[]{3, "John", "Adwards"});
	    data.put("5", new Object[]{4, "Brian", "Schultz"});

	    Set<String> keyset = data.keySet();

	    int rownum = 0;
	    for (String key : keyset) 
	    {
	        XSSFRow row = sheet.createRow(rownum++);

	        Object[] objArr = data.get(key);

	        int cellnum = 0;

	        for (Object obj : objArr) 
	        {
	            XSSFCell cell = row.createCell(cellnum++);
	            if (obj instanceof String) 
	            {
	                cell.setCellValue((String) obj);
	            }
	            else if (obj instanceof Integer) 
	            {
	                cell.setCellValue((Integer) obj);
	            }
	        }
	    }
	    try 
	    {
	        //Write the workbook in file system
	        FileOutputStream out = new FileOutputStream(new File(url));
	        workbook.write(out);
	        out.close();
	        
	    } 
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	
	public void readExcel(String url) {
		try {
		FileInputStream file = new FileInputStream(new File(url));

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) 
            {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) 
                {
                    case CellType.STRING:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case CellType.NUMERIC:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                }
            }
            System.out.println("");
        }
        file.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	}
