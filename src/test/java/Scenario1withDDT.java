import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario1withDDT {

	public static void main(String[] args) throws Throwable {
		//step1: read all the necessary script data
		/*read data from property files*/
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		String BROWSER=p.getProperty("browser");
		/*read data from excel*/
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String LASTNAME = wb.getSheet("Contact").getRow(1).getCell(2).getStringCellValue();
		
		WebDriver driver = null;
		
		//step 2: launch the browser
		//run time polymorphism-driver
		//overriding = two classes
		//overloading = two methods
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER+"Launched");
		}
		else if(BROWSER.equalsIgnoreCase("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println(BROWSER+"Launched");
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
		    WebDriverManager.firefoxdriver().setup();
		    driver = new FirefoxDriver();
		    System.out.println(BROWSER+"Launched");
		}
		else
		{
			System.out.println("invalid Browser name");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("(//td[@style='padding-right:0px;padding-left:10px;'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contactHeader.contains(LASTNAME))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
		WebElement signout = driver.findElement(By.xpath("(//td[@class='small'])[2]"));
		Actions act = new Actions(driver);
		act.moveToElement(signout).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("signout successfully");
				
	}

}
