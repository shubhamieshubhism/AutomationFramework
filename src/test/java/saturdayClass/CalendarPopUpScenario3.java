package saturdayClass;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPopUpScenario3 {

	public static void main(String[] args) throws Throwable {
		
		
		Date d = new Date();
		String dateNow = d.toString();
		System.out.println(dateNow);
		String[] dArr = d.toString().split(" ");
		String currentDate=dArr[0]+" "+dArr[1]+" "+dArr[2]+" "+dArr[5];
		System.out.println(currentDate);
		
		
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
		driver.findElement(By.xpath("(//p[text()='23'])[1]"));
		//dynamic Xpath.
		String month = "September 2023";
		String day = "23";
				//driver.findElement(By.xpath("//div[text()='"+month+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+day+"']")).click();
		driver.findElement(By.xpath("(//div[text()='"+month+"']/ancestor::div[@class='DayPicker-Months']/descendant::p[.='"+day+"'])")).click();
    
	}

}

