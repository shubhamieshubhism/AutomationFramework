import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XpathPractice {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Iphone");
		driver.findElement(By.id("nav-search-submit-button")).click();
		List<WebElement> phonelist = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		List<WebElement> pricelist = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
		/*
		 * for(int i=1;i<=phonelist.size();i++) { System.out.println(phonelist); }
		 */
		
		for(int i=0;i<phonelist.size();i++) {
			System.out.println(phonelist.get(i).getText());
			System.out.println(pricelist.get(i).getText());
			System.out.println();
			
		}
	    
	}

}
