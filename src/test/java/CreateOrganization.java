import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganization {

	@Test
	public void createOrganizationTest() throws Throwable {

		// step1:create all the objects
		JavaUtility jutil = new JavaUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		PropertyFileUtility putil = new PropertyFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// step2:Read the required data
		String BROWSER = putil.readDataFrompropertyFile("browser");
		String URL = putil.readDataFrompropertyFile("url");
		String USERNAME = putil.readDataFrompropertyFile("username");
		String PASSWORD = putil.readDataFrompropertyFile("password");

		String ORGNAME = eutil.readDataFromExcelFile("Organization", 1, 2) + jutil.getRandomNumber();

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

		// step5:login to application

		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step6:navigate to organization link text
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();

		// step7:click on create organization look up image
		driver.findElement(By.xpath("(//td[@style='padding-right:0px;padding-left:10px;'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);

		// step8:save
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// step9:validate
		Thread.sleep(1000);
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contactHeader.contains(ORGNAME)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}

		// step10:logout
		WebElement signout = driver.findElement(By.xpath("(//td[@class='small'])[2]"));
		wutil.mouseHoverAction(driver, signout);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		System.out.println("Signout successfully");
	}

}
