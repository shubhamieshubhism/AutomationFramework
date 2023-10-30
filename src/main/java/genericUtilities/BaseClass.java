package genericUtilities;

/**
 * This class consist of all basic configuration annotaion of testNG
 * 
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass {
	public PropertyFileUtility putil = new PropertyFileUtility();
	public ExcelFileUtility eutil = new ExcelFileUtility();
	public WebDriverUtility wutil = new WebDriverUtility();
	public JavaUtility jutil = new JavaUtility();
	public WebDriver driver = null;
	
	//used in listeners
	public static WebDriver sdriver;

	@BeforeSuite(groups = {"SmokeSuite","ReggressionSuite"})
	public void bsConfig() throws Throwable {
		System.out.println("----DB connection Successful----");

	}
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(alwaysRun = true)
	public void bcConfig(/*String BROWSER*/) throws Throwable {
		String BROWSER = putil.readDataFrompropertyFile("browser");
		String URL = putil.readDataFrompropertyFile("url");
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
		sdriver=driver;
		driver.get(URL);

	}

	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws Throwable {
		String USERNAME = putil.readDataFrompropertyFile("username");
		String PASSWORD = putil.readDataFrompropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("---login successful----");

	}

	@AfterMethod(alwaysRun = true)
	public void amConfig() throws Throwable {
		HomePage hp = new HomePage(driver);
		hp.logoutOfApp(driver);
		System.out.println("logout successfully");

	}
	//@AfterTest
	@AfterClass(alwaysRun = true)
	public void acConfig() {
		driver.quit();
		System.out.println("browser has been closed successfully");

	}

	@AfterSuite(groups = {"SmokeSuite","ReggressionSuite"})
	public void asConfig() {
		System.out.println("---DB Connection Closed---");
	}

}
