import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario5 {

	public static void main(String[] args) throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("(//td[@style='padding-right:0px;padding-left:10px;'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("heer");
		//WebElement orga = driver.findElement(By.xpath("//a[.='Organizations']"));
		
		WebDriverUtility wd=new WebDriverUtility();
		Thread.sleep(3000);
		wd.switchToWindow(driver, "HP");
		
		
		/*Thread.sleep(2000);
		String mainwin=driver.getWindowHandle();
		System.out.println(mainwin);
		Set<String> allwin = driver.getWindowHandles();
		System.out.println(allwin);
		
		for(String a: allwin)
		{
			if(!mainwin.equals(a))
			{
				driver.switchTo().window(a);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[.='HP']")).click();
			}
		}
		driver.switchTo().window(mainwin);*/
		/*Actions act = new Actions(driver);
		act.moveToElement(orga);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(1000);*/
		WebElement signout = driver.findElement(By.xpath("(//td[@class='small'])[2]"));
		Actions act1 = new Actions(driver);
		act1.moveToElement(signout).click().perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	

	}

}
