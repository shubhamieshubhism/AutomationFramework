package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {
	
	@FindBy(name = "accountname")
	private WebElement organizationName;
	
	@FindBy(name = "industry")
	private WebElement industryDropDown;
	
	@FindBy(name = "accounttype")
	private WebElement typeDropDown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public WebElement getOrganizationName() {
		return organizationName;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	public void createNewOrganization(String ORGNAME) {
		organizationName.sendKeys(ORGNAME);
		saveBtn.click();
	}
	public void createNewOrganization(String ORGNAME,String INDUSTRY) {
		organizationName.sendKeys(ORGNAME);
		handleDropDown(INDUSTRY, industryDropDown);
		saveBtn.click();	
	}
	
	
	
	

}
