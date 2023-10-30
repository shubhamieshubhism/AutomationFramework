package contactsTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepository.ContactInfoPage;
import objectRepository.ContactPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationPage;
@Listeners(genericUtilities.ListenerImplimentationClass.class)
public class CreateContactWithOrganizationUsingBaseClassTest extends BaseClass {
	@Test(groups = "ReggressionSuite")
	public void CreateContactWithOrganization() throws Throwable {

		String ORGNAME = eutil.readDataFromExcelFile("Contact", 7, 2) + jutil.getRandomNumber();
		String LASTNAME = eutil.readDataFromExcelFile("Contact", 7, 3);

		HomePage hp = new HomePage(driver);
		hp.clickOnOrganization();
		Reporter.log("clicked on organization");
	

		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnOrganizationLookUpImg();
		Reporter.log("clicked on organization look up img");

		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME);
		Reporter.log("New organzation created");

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgHeader = oip.getHeaderText();
		if (orgHeader.contains(ORGNAME)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}

		hp.clickOnContact();

		ContactPage cp = new ContactPage(driver);
		cp.ClickOnCreateContactLookUpImg();
		Reporter.log("Clicked on create contact lookup img");
		
		//just to check the failure otherwise didn't necessary
		//Assert.fail();

		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.CreateNewContact(LASTNAME);
		Reporter.log("New contact created");

		ContactInfoPage cip = new ContactInfoPage(driver);
		String contactHeader = cip.getHeaderText();
		//Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader);
		
		
	}
	@Test
	public void demo() {
		System.out.println("===pass===");
	}

}
