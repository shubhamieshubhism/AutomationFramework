package organizationTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationPage;

public class CreateOrganizationWithIndustryTest {

	public static void main(String[] args) throws Throwable {
		JavaUtility jutil= new JavaUtility();
		PropertyFileUtility putil=new PropertyFileUtility();
		ExcelFileUtility eutil= new ExcelFileUtility();
		WebDriverUtility wutil =new WebDriverUtility();
		
		
		String URL = putil.readDataFrompropertyFile("url");
		String BROWSER = putil.readDataFrompropertyFile("browser");
		String USERNAME=putil.readDataFrompropertyFile("username");
		String PASSWORD=putil.readDataFrompropertyFile("password");
		
		String ORGNAME=eutil.readDataFromExcelFile("Contact", 7, 2)+jutil.getRandomNumber();
		
		WebDriver driver = null;
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println(BROWSER+"launched");
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			System.out.println(BROWSER+"launched");
		}else if(BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println(BROWSER+"launched");
		}else {
			System.out.println("invalid input");
		}
		
		wutil.maximizeWindow(driver);
		wutil.waitForPageLoad(driver, null);
		
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganization();
		
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnOrganizationLookUpImg();
		
		CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME, "Banking");
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String orgHeader = oip.getHeaderText();
		if(orgHeader.contains(ORGNAME)) {
			System.out.println("Pass");
		}else {
			System.out.println("fail");
		}
		
		hp.logoutOfApp(driver);
		
		
		Thread.sleep(3000);
		driver.quit();
		
		
		
		

	}

}
