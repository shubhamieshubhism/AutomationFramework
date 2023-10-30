package organizationTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class CreateMultipleOrganizationWithIndustryTest {
	JavaUtility jutil = new JavaUtility();
	PropertyFileUtility putil = new PropertyFileUtility();
	ExcelFileUtility eutil = new ExcelFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();

	@Test(dataProvider = "getData")

	public void createMultipleOrg(String ORG, String INDUSTRYNAME) throws Throwable {
		WebDriver driver = null;
		String URL = putil.readDataFrompropertyFile("url");
		String BROWSER = putil.readDataFrompropertyFile("browser");
		String USERNAME = putil.readDataFrompropertyFile("username");
		String PASSWORD = putil.readDataFrompropertyFile("password");

		String ORGNAME = ORG + jutil.getRandomNumber();

		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + "launched");
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER + "launched");
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + "launched");
		} else {
			System.out.println("invalid input");
		}

		wutil.maximizeWindow(driver);
		wutil.waitForPageLoad(driver, null);

		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganization();

		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnOrganizationLookUpImg();

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME, INDUSTRYNAME);
		wutil.takeScreenShot(driver, ORGNAME);

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgHeader = oip.getHeaderText();
		if (orgHeader.contains(ORGNAME)) {
			System.out.println(orgHeader);
			System.out.println("Organization Created");
		} else {
			System.out.println("fail");
		}

		hp.logoutOfApp(driver);

	}

	@DataProvider
	public Object[][] getData() throws Throwable {
		return eutil.readMultipleData();
	}
}