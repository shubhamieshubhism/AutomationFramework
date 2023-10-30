package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * this class consist of all generic methods 
 * to web driver action
 * @author Shubham
 */

public class WebDriverUtility {
	
	/*WebDriver driver = null;
	browser launched and maximize
	driver is null due to above meethod
	if you declare globally driver will be null
	therefore parameterizing it is a better option
	because we want this method after launching browser
	Due to parameterizing it we are getting updated driver.*/
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
		
	}
	/**
	 * This method will mininize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will wait for the page to load
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver, WebElement element) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	/**
	 * This method will wait for a particular element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait for particular element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver ,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will handle dropdown by index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element , int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	
	}
	
	/**
	 * This method will handle dropdown by value
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element,String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	/**
	 * This method will handle dropdown by visibletext
	 * @param visibletext
	 * @param element
	 */
	public void handleDropDown(String visibletext,WebElement element) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibletext);
	}
	
	
	/**
	 * This method will perform mouse hovering action
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void moveAndClick(WebDriver driver) {
		Actions act = new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
	}
	/**
	 * This method will use to right click
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.contextClick().perform();
	}
	/**
	 * This method will perform double click
	 */
	public void doubleClickActio(WebDriver driver) {
		Actions act = new Actions(driver);
		act.doubleClick().perform();		
	}
	/**
	 * This method will perform drag and drop
	 * @param driver
	 * @param scrEle
	 * @param dstEle
	 */
	public void dragAndDropAction(WebDriver driver,WebElement scrEle,WebElement dstEle) {
		Actions act = new Actions(driver);
		act.dragAndDrop(scrEle, dstEle).perform();
	}
	/**
	 * This method will handle frames by element
	 * @param driver
	 * @param element
	 */
	public void frameHandling(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will handle frames by index
	 * @param driver
	 * @param element
	 */
	public void frameHandling(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will handle frames by Name or ID
	 * @param driver
	 * @param element
	 */
	public void frameHandling(WebDriver driver,String NameOrID) {
		driver.switchTo().frame(NameOrID);
	}
	
	/**
	 * this method is used for scroll down
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void scrolldownAction(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500);", "");
	}
	
	/**
	 * this method is used for scroll up
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void scrollUpAction(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-500);", "");
	}
	
	/**
	 * This method will handle the accept in alert
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will handle the dismiss in alert
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will get the alert text and return it to caller
	 * @param driver
	 * @return 
	 */
	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method will take screenshot and return the dst path
	 * @param driver
	 * @param ScreenshotName
	 * @return
	 * @throws Throwable
	 */
	public String takeScreenShot(WebDriver driver,String ScreenshotName) throws Throwable {
	TakesScreenshot ts = (TakesScreenshot)driver;
	File scr = ts.getScreenshotAs(OutputType.FILE);
	File dst = new File(".\\Screenshots\\"+ScreenshotName+".png");
	//FileUtils.copyDirectory(scr, dst);
	Files.copy(scr, dst);//from common io dependency
	return dst.getAbsolutePath(); //use for extent report
	}
	
	
	/**
	 * This method will switch from one window 
	 * to another window based on window title
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWinTitle) {
		//step1:get all the window IDs
		Set<String> allWinIDs = driver.getWindowHandles();
		
		//step2:navigate through each window
		
		for (String winID : allWinIDs) {
			
			//step3: switch to each window and capture the title
			String actTitle = driver.switchTo().window(winID).getTitle();
			
			//step4:compare act title with expected partial title
			if(actTitle.contains(partialWinTitle)) {
				break;
			}
			
		}
	}
	
	
	
}
