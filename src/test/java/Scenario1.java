import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario1 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("(//td[@style='padding-right:0px;padding-left:10px;'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("shubham2");
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contactHeader.contains("shubham2"))
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
