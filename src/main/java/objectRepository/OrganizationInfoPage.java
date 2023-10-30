package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	@FindBy(xpath ="//span[@class='dvHeaderText']" )
	private WebElement OrgHeaderText;
	
	public OrganizationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public WebElement getOrgHeaderText() {
		return OrgHeaderText;
	}
	//Business library
	/**
	 * this method will capture the text and return it to caller
	 * @return 
	 */
	public String getHeaderText() {
		return OrgHeaderText.getText();
	}

}
