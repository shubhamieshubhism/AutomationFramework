import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario3 {

	public static void main(String[] args) throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
		driver.findElement(By.xpath("(//td[@style='padding-right:0px;padding-left:10px;'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("xyz");
		WebElement industry = driver.findElement(By.xpath("//select[@name='industry']"));
		Select sel = new Select(industry);
		sel.selectByIndex(4);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(1000);
		WebElement signout = driver.findElement(By.xpath("(//td[@class='small'])[2]"));
		Actions act = new Actions(driver);
		act.moveToElement(signout).click().perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();

	}

}
