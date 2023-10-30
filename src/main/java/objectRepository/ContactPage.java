package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement CearteContactLookUpImg;
	
	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver,this);		
	}

	public WebElement getCearteContactLookUpImg() {
		return CearteContactLookUpImg;
	}
	public void ClickOnCreateContactLookUpImg() {
		CearteContactLookUpImg.click();
	}
	

}
