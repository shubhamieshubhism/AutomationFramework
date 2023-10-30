package saturdayClass;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPopUpScenario2 {

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
		driver.findElement(By.xpath("//span[text()='Departure']")).click();
		Thread.sleep(1000);
		//WebElement next = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));
		//unconditional for loop 
		for( ; ; ) {
			try {
				driver.findElement(By.xpath("//div[@aria-label='Sat Apr 06 2024']"));
				break;
			}catch (Exception e) {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
				Thread.sleep(1000);
			
			}
		}
		
		//driver.findElement(By.xpath("//div[@aria-label='Tue Dec 12 2023']")).click();


	}

}
