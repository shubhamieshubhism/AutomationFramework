package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage  {
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement CreateOrgLookUpImg;

public OrganizationPage(WebDriver driver) {
	PageFactory.initElements(driver,this);
}

public WebElement getCreateOrgLookUpImg() {
	return CreateOrgLookUpImg;
}
//business library
/**
 * this method will click on create org look up img
 */
public void clickOnOrganizationLookUpImg() {
	CreateOrgLookUpImg.click();
}



}


