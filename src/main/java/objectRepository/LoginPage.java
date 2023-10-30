package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	//rule no 1
	
	//rule no 2 - declaration 
	@FindBy(name = "user_name") 
	private WebElement userNameEdt;
	
	@FindBy(name = "user_password")
	private WebElement passwordEdt;
	
	@FindAll({@FindBy(id = "submitButton"),@FindBy(xpath = "//input[@type='submit']")})
     private WebElement loginBtn;
	
	//rule no 3 - initialization
	public LoginPage(WebDriver driver) {
	PageFactory.initElements(driver,this);	
	}
	//rule no 4 - utilization

	public WebElement getUserNameEdt() {
		return userNameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Business libraries -genric method according to the 
	//need of project
	/**
	 * This method will login to Application 
	 * @param USERNAME
	 * @param PASSWORD
	 */
	public void loginToApp(String USERNAME,String PASSWORD) {
		userNameEdt.sendKeys(USERNAME);
		passwordEdt.sendKeys(PASSWORD);
		loginBtn.click();	
	}
	
	

	


}