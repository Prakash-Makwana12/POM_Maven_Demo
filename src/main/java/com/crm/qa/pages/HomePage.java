package com.crm.qa.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath = "//font[contains(text(),'User: Naveen K')]")
	@CacheLookup
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealslink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath="//a[text()='New Contact']")
	WebElement newContactLink;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle()
	{
		return driver.getTitle();
		
	}
	
	public boolean verifyCorrectUserName()
	{
		return userNameLabel.isDisplayed();
		
	}
	
	public ContactsPage clickOnContactsLink()
	{
		
		contactsLink.click();
		return new ContactsPage();
		
		
	}
	
	public DealsPage clickOnDealsLink()
	{
		dealslink.click();
		return new DealsPage();
	}
	
	public TaskPage clickOnTasksLink()
	{
		tasksLink.click();
		return new TaskPage();
	}
	
	public void cliclOnNewContactLink()
	{
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}

}
