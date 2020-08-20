/*================================
* @ Author: Jairaj Kuriackose
* @Page Class: LoginPage
=================================*/

package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

// Link LoginPage to Parent Class "TestBase" using extends keyword
public class LoginPage extends TestBase{
	
	
	//Declaring Page Factory - OR: ( using @FindBy() )
	// Here we will declare all the elements to be used in LoginPage
	
	@FindBy(name="email")
	@CacheLookup
	WebElement username;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement password;
	
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']")
	@CacheLookup
	WebElement loginBtn;
	
	@FindBy(linkText="Sign Up") 
	WebElement signUplnk;
	
	@FindBy(xpath="//a[contains(text(),'Classic CRM')]")
	WebElement classicCRMlnk;
	
	

		// Here we will create methods for all actions we will perform on this page
		// Constructor for this class
		public LoginPage() {
			
			//Initialize your page Factory using "initElements". Now driver can can be used for these elements.
			PageFactory.initElements(driver, this); // driver is coming from Base class & this means: current class objects.	
		}
		
			
		//Declare Actions: Action 1
		//Create methods for "LoginPage"
		public String validateLoginPageTitle() {
			return driver.getTitle();	
		}
		
		
		//Declare Actions: Action 2
		//Validate Classic CRM Link
		// When the method is not returning anything. mention it as "void"
		// Be careful on the return Types
		public boolean validateClassicCRMLnk() {
			return classicCRMlnk.isDisplayed();
		}
		
	
		//Declare Actions: Action 3
		//Create methods for "LoginPage"
		// This method should return Homepage (next related page)
		public HomePage login(String user, String pwd) {
			username.sendKeys(user);
			password.sendKeys(pwd);
			loginBtn.click(); // after clicking on login we will navigate to Homepage (i.e Homepage class object
			return new HomePage();
		}

	
	} // End of LoginPage Class
