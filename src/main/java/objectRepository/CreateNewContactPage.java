package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	@FindBy(name = "lastname")
	private WebElement LastName;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public WebElement getLastName() {
		return LastName;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	public void CreateNewContact(String LASTNAME) {
		LastName.sendKeys(LASTNAME);
		SaveBtn.click();
	}
	

}
