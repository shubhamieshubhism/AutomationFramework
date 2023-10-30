import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ReadDataFromExcelFile {

	@Test
	public void readDataFromExcelFileTest() throws Throwable {
		//step1: open the doc in java readable format
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testdatapractice.xlsx");
		//step2: create a workbook 
		Workbook wb = WorkbookFactory.create(fis);
		//step3: navigate to a required sheet
		Sheet sh = wb.getSheet("Contact");
		//step4: navigate to required row
		Row rw = sh.getRow(1);
		//step5: navigate to required cell
		Cell cl = rw.getCell(1);
		//step6: capture the value and print
		//String value = cl.getStringCellValue();
		//System.out.println(value);
		
		cl.setCellValue("mamger");
		FileOutputStream fis1 = new FileOutputStream(".\\src\\test\\resources\\testdatapractice.xlsx");
	    wb.write(fis1);
	    wb.close();

	}

}
