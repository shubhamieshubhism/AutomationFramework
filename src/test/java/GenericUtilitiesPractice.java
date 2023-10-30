import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;

public class GenericUtilitiesPractice {

	public static void main(String[] args) throws Throwable {
		PropertyFileUtility putil = new PropertyFileUtility();
		String URL=putil.readDataFrompropertyFile("url");
		System.out.println(URL);
		String BROWSER=putil.readDataFrompropertyFile("browser");
		System.out.println(BROWSER);
		
		ExcelFileUtility eutil=new ExcelFileUtility();
		String data = eutil.readDataFromExcelFile("Organization", 1,2);
		System.out.println(data);
		
		JavaUtility jutil = new JavaUtility();
		int r = jutil.getRandomNumber();
		System.out.println(r);
		
		JavaUtility jutil1= new JavaUtility();
		String date = jutil1.getSystemDate();
		System.out.println(date);
		
	
		
		


	}

}
