package contactsTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInfoPage;
import objectRepository.ContactPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateContactTest {

	@Test
	public void createContactTest() throws Throwable {

		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		PropertyFileUtility putil = new PropertyFileUtility();
		// read the required data
		String URl = putil.readDataFrompropertyFile("url");
		String BROWSER = putil.readDataFrompropertyFile("browser");
		String USERNAME = putil.readDataFrompropertyFile("username");
		String PASSWORD = putil.readDataFrompropertyFile("password");

		String LASTNAME = eutil.readDataFromExcelFile("Contact", 7, 2) + jutil.getRandomNumber();
		// launch the browser
		WebDriver driver = null;

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
			System.out.println("invalid browser name");
		}
		wutil.maximizeWindow(driver);
		wutil.waitForPageLoad(driver, null);

		// load url
		driver.get(URl);
		// login
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		// click on contact lookup img
		HomePage hp = new HomePage(driver);
		hp.clickOnContact();
		ContactPage cp = new ContactPage(driver);
		cp.ClickOnCreateContactLookUpImg();
		// create new contact
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.CreateNewContact(LASTNAME);

		// validation
		ContactInfoPage cip = new ContactInfoPage(driver);
		String contactHeader = cip.getHeaderText();
		if (contactHeader.contains(LASTNAME)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		// click on contact link
		hp.logoutOfApp(driver);
		Thread.sleep(3000);
		driver.quit();

	}

}
