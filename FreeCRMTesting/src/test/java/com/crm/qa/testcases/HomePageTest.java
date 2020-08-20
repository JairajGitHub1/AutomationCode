package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
		
	LoginPage loginPage; // since we want to use this variable across the class hence, declaring here.
	HomePage homePage; // since login process once completed will return homepage class
	TestUtil testUtil; // TestUtil class object
	ContactsPage contactsPage; // ContactsPage Class Object initialized
	
	
	// HomePage Constructor with super() keyword (shortcut to import all dependent class: Ctrl + Shift + O)
	public HomePageTest(){		
		
		//Call Super Keyword to initialize properties declared in TestBase class constructor
		// It is compulsory to call TestBase class constructor
		//Note: Constructor call must be the first statement in the code
		// Super() class call will initialize the properties
		
		super(); // ctrl + click to check navigation (TestBase Constructor)
		System.out.println("###################### HomePageTest Class: Inside HomePageTest constructor ######################");
	}
	
	
	
	//Test cases should be independent of each other. Hence, launching, login, closing browser for each test cases is a good practice.
	// TestNG Annotations
		@BeforeMethod
		
		public void setUp() {
			System.out.println("HomePageTest Class: inside SetUp Method");

			// Call initialization method from TestBase Class
			initialization();
			System.out.println("HomePageTest Class: initialization completed");
			
			// Before testing Homepage: You need to Login Successfully
			// Create object of "Login Page Class" i.e. (LoginPage.java)		
			// LoginPage class constructor
			// This loginPage object will help me call all login methods
			loginPage = new LoginPage(); // (ctrl + click) will take you to LoginPage constructor
			System.out.println("HomePageTest Class: setUp method: an object is created for Loginpage.java (class)");
			
			testUtil = new TestUtil(); // initializing testUtil. This will help call methods defined in testUtil
			
			// HomePage Class object will be returned
			homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
			System.out.println("HomePageTest Class: setUp method: Login Successful");
		}
		
		
		
		
		//All Test Methods must end with the name "Test"
		@Test(priority=1)
		public void verifyHomePageTitleTest(){
			String homePageTitle = homePage.verifyHomePageTitle();
			// Assert method can have 3 parameters
			Assert.assertEquals(homePageTitle, "Cogmento CRM","Home page title not matched");
			System.out.println("HomePageTest Class : verifyHomePageTitleTest method: Assertion Completed: " + homePageTitle);
		}
		
		
		
		
		@Test(priority=2)
		public void verifyUserNameTest(){
			
			String Homepageuserdisplayed = homePage.verifyUserNameDisplayed();
			//testUtil.switchToFrame();
			Assert.assertTrue(homePage.verifyCorrectUserName());
			Assert.assertEquals(Homepageuserdisplayed, "Jai K","Username @ Homepage not matched");
			System.out.println("HomePageTest Class : Verify User displayed: " + Homepageuserdisplayed);
		}
		
		
		
		
		@Test(priority=3)
		public void verifyContactsLinkTest(){
			//testUtil.switchToFrame();
			//clickOnContactsLink() method is returning contactsPage object. Hence store in a variable: contactsPage.
			//contactsPage is an object of ContactsPage. It is declared above.
			contactsPage = homePage.clickOnContactsLink(); 
		}

		
		
		@AfterMethod
		public void teardown() {
			
			driver.quit();
			System.out.println("<========================== HomePage Teardown Completed: Quit the driver and close all associated windows ==========================>");
		}
	
	

} // End of HomePageTest Class
