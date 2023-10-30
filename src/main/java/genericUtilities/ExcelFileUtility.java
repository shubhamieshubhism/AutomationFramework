package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consisit of generic method to read data from excel file
 * 
 * @author Shubham
 */
public class ExcelFileUtility {
	/**
	 * This method will read data from excel file and return the value to caller
	 * 
	 * @throws Throwable
	 */

	public String readDataFromExcelFile(String sheetname, int rownum, int cellnum) throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		return value;

	}
	/**
	 * This method will read multiple data from excel sheet at time
	 * used for data provider
	 * @return
	 * @throws Throwable
	 */

	public Object[][] readMultipleData() throws Throwable {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("MultipleOrganizations");
		int lastRow = sh.getLastRowNum();
		int lastCell = sh.getRow(0).getLastCellNum();

		Object[][] data = new Object[lastRow][lastCell];
		for (int i =0 ; i < lastRow; i++) {
			// whenever indexing starting from 0 <= other wise <
			for (int j = 0; j < lastCell; j++) {
				data[i][j] = sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
}