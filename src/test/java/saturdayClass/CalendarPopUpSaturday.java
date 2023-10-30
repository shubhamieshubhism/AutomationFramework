package saturdayClass;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPopUpSaturday {

	public static void main(String[] args) throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com");
		//WebElement noti = driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div"));
		Actions act = new Actions(driver);
		//act.moveToElement(noti).perform();
		act.moveByOffset(10, 10).click().perform();
		
		Thread.sleep(1000);
		
		/*WebElement from = driver.findElement(By.xpath("//input[@placeholder='From']"));
		from.click();
		from.sendKeys("mumbai");
		driver.findElement(By.xpath("//p[text()='Mumbai, India']")).click();
		
		WebElement to = driver.findElement(By.xpath("//input[@placeholder='To']"));
		to.click();
		to.sendKeys("delhi");
		driver.findElement(By.xpath("//p[text()='New Delhi, India']")).click();*/
        driver.findElement(By.xpath("//span[text()='Departure']")).click();
		driver.findElement(By.xpath("//div[@aria-label='Wed Oct 04 2023']")).click();

	}

}
