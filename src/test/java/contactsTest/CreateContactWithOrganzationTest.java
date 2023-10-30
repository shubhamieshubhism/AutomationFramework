package contactsTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInfoPage;
import objectRepository.ContactPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationPage;

public class CreateContactWithOrganzationTest extends BaseClass {

	@Test
	public void CreateContactWithOrganization() throws Throwable {
		JavaUtility jutil = new JavaUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		PropertyFileUtility putil = new PropertyFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// step2:Read the required data
		String BROWSER = putil.readDataFrompropertyFile("browser");
		String URL = putil.readDataFrompropertyFile("url");
		String USERNAME = putil.readDataFrompropertyFile("username");
		String PASSWORD = putil.readDataFrompropertyFile("password");

		String ORGNAME = eutil.readDataFromExcelFile("Contact", 7, 2) + jutil.getRandomNumber();
		String LASTNAME = eutil.readDataFromExcelFile("Contact", 7, 3);
		WebDriver driver = null;

		// step 3: launch the browser
		// run time polymorphism-driver
		// overriding = two classes
		// overloading = two methods
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER + "Launched");
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER + "Launched");
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println(BROWSER + "Launched");
		} else {
			System.out.println("invalid Browser name");
		}

		wutil.maximizeWindow(driver);
		wutil.waitForPageLoad(driver, null);

		// step4:load url
		driver.get(URL);
		// step 5:login
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		// step6: click on organization link

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganization();

		// step7: click on create organization lookup img
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnOrganizationLookUpImg();

		// step8:create new org with mandatory fields
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME);

		// step 9: validate for org
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgHeader = oip.getHeaderText();
		if (orgHeader.contains(ORGNAME)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		// step10:click on contacts link
		hp.clickOnContact();

		// step11:click on contact lookup img
		ContactPage cp = new ContactPage(driver);
		cp.ClickOnCreateContactLookUpImg();

		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.CreateNewContact(LASTNAME);

		ContactInfoPage cip = new ContactInfoPage(driver);
		String contactHeader = cip.getHeaderText();
		if (contactHeader.contains(LASTNAME)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}

		hp.logoutOfApp(driver);

	}

}
