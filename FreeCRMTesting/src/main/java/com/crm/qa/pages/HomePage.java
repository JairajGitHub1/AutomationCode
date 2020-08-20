/*================================
* @ Author: Jairaj Kuriackose
* @Page Class: HomePage
=================================*/

package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	//Page Factory - OR:
	@FindBy(className = "user-display")
	@CacheLookup
	WebElement userNameLabel;

	@FindBy(xpath = "//span[contains(text(),'Contacts')]")
	@CacheLookup
	WebElement contactsLink;
	
//	@FindBy(xpath = "//button[contains(text(),'New')]")
//	WebElement newContactBtn;
	

	@FindBy(xpath = "//span[contains(text(),'Deals')]")
	@CacheLookup
	WebElement dealsLink;

	@FindBy(xpath = "//span[contains(text(),'Tasks')]")
	@CacheLookup
	WebElement tasksLink;

	
	
	// Create a constructor of Homepage to Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions & Operations:
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	
	public String verifyUserNameDisplayed(){
		return userNameLabel.getText();
	}
	
	//We need to Write ContactsPage instead of Void as the return type is ContactsPage object
	public ContactsPage clickOnContactsLink(){
		contactsLink.click();
		return new ContactsPage(); // this method returns Contacts Page Object 
	}
	
	//We need to Write DealsPage instead of Void as the return type is DealsPage object
	public DealsPage clickOnDealsLink(){
		dealsLink.click();
		return new DealsPage(); // this method returns Deals Page Object 
	}
	
	//We need to Write TasksPage instead of Void as the return type is TasksPage object
	public TasksPage clickOnTasksLink(){
		tasksLink.click();
		return new TasksPage(); // this method returns Tasks Page Object 
	}
	
	//This method is used for mouse over
//	public void clickOnNewContactLink(){
//		Actions action = new Actions(driver);
//		action.moveToElement(contactsLink).build().perform();
//		newContactBtn.click();
//		
//	}
	
	
}// End of HomePage Class
