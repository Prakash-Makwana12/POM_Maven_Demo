package com.crm.qa;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtils;

public class ContactsPageTest extends TestBase {
	
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtils testUtil;
	ContactsPage contactsPage;
	
	String sheetName="Contacts";
	
	public ContactsPageTest() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initalization();
		 loginPage = new LoginPage();
		 testUtil = new TestUtils();
		 contactsPage = new ContactsPage();
		 
		 
		 homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));	
		 
		 testUtil.switchToFrame();
		 contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContacsPageLabelTest()
	{
		Assert.assertTrue( contactsPage.verifyContactsLabel(),"Contacs label is missing on the Page.");
	}
	
	@Test(priority=2)
	public void selectContactsByNameTest()
	{
		contactsPage.selectContactsByName("Tom Peter");
	}
	@Test(priority=3)
	public void selectMultipleContactsByNameTest()
	{
		contactsPage.selectContactsByName("Tom Peter");
		contactsPage.selectContactsByName("anjali Jiyani");
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object data[][] = TestUtils.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getData")
	public void validateCreateNewContact(String title,String fName,String lName, String com)
	{
		homePage.cliclOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		
		contactsPage.createNewContact(title, fName, lName, com);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
