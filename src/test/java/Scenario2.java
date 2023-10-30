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

public class Scenario2 {

	public static void main(String[] args) throws Throwable {
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
		String LASTNAME = wb.getSheet("Contact").getRow(3).getCell(2).getStringCellValue();
		
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
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
		driver.findElement(By.xpath("(//td[@style='padding-right:0px;padding-left:10px;'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(1000);
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
		act.moveToElement(signout).click().perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		System.out.println("Signout successfully");

	}

}
