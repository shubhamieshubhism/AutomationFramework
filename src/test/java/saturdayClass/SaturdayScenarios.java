package saturdayClass;

import java.util.List;

import org.apache.poi.hpsf.Property;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.LoginPage;

public class SaturdayScenarios {

	public static void main(String[] args) throws Throwable {
		JavaUtility jutil = new JavaUtility();
		PropertyFileUtility putil = new PropertyFileUtility(); 
		WebDriverUtility wutil = new WebDriverUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		
		String URL = putil.readDataFrompropertyFile("url");
		String USERNAME=putil.readDataFrompropertyFile("username");
		String PASSWORD=putil.readDataFrompropertyFile("password");
		String BROWSER=putil.readDataFrompropertyFile("browser");
		
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER+"launched");
		}
		else if(BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER+"launched");
		}
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new ChromeDriver();
			System.out.println(BROWSER+"launched");
		}else {
			System.out.println("invalid browser");
		}
		wutil.maximizeWindow(driver);
		wutil.waitForPageLoad(driver, null);
		
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
		//scenario 1 :check on all the check boxes
		//driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[1]/input[@type='checkbox']")).click();
		
		//scenario 2 :check on 5th the check boxes
		//driver.findElement(By.id("8")).click();
		
		//scenario 3 :capture all the organization names and prnt in console. 
		/*List<WebElement> orgnames = driver.findElements(By.xpath("//a[@title='Organizations']"));
		for (WebElement list : orgnames) {
			System.out.println(list.getText());
			
		}*/
		
		//scenario 4 :Click on the last check box
		//driver.findElement(By.id("19")).click();
		
		/*//scenario 5 :click on 8th check box and capture the Organization name and delete that organization  
		driver.findElement(By.id("15")).click();
		WebElement orgnm = driver.findElement(By.linkText("Superman"));
		System.out.println(orgnm.getText());
		driver.findElement(By.xpath("//input[@value='Delete']")).click();
		Alert alt = driver.switchTo().alert();
		alt.accept();*/
		WebElement admimg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.mouseHoverAction(driver, admimg);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();


	}

}
