package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;


public class LoginPageTest extends TestBase{
	
	LoginPage loginPage; // since we want to use this variable across the class hence, declaring here.
	HomePage homePage; // since login process once completed will return homepage class


	//@Rule public TestName testName = new TestName();
	
	// LoginPage Constructor (shortcut to import all dependent class: Ctrl + Shift + O)
	public LoginPageTest() {
		
		//Call Super Keyword to initialize properties declared in TestBase class constructor
		// It is compulsory to call TestBase class constructor
		//Note: Constructor call must be the first statement in the code
		// Super() class call will initialize the properties
		
		super(); // ctrl + click to check navigation (TestBase Constructor)
		System.out.println("###################### LoginPageTest Class: Inside LoginPageTest constructor ######################");
		log.info("Logger: ========= Chrome Driver Launched =========");
	}
	
	

	
	// TestNG Annotations
	@BeforeMethod
	
	public void setUp() {
		System.out.println("LoginPageTest Class: inside SetUp Method");

		// Call initialization method from TestBase Class
		initialization();
		System.out.println("LoginPageTest Class: initialization completed");
		
		// Create object of "Login Page Class" i.e. (LoginPage.java)		
		//LoginPage class constructor
		// This loginPage object will help me call all login methods		
		loginPage = new LoginPage();
		System.out.println("LoginPageTest Class: setUp method: an object is created for Loginpage.java (class)");
	}
	
	//All Test Methods must end with the name "Test"
	@Test(priority=1)
	public void loginPageTileTest() {
		
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		log.info("$$$$$$$$$$$$$$$$$$$$$             Login Page Tile Test         $$$$$$$$$$$$$$$$$$$$$$$$$");
		//log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		 
		//We had already created this method in the LoginPage Class
		// Let us store the value returned on a String
		String title = loginPage.validateLoginPageTitle();
		
		// Let us write assertion here: It is used for validate: Text
		Assert.assertEquals(title, "Cogmento CRM");	
		
		System.out.println("LoginPageTest Class: loginPageTileTest method: Assertion Completed: " + title);
		
		log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");		 
		log.info("X");
		log.info("X");
		log.info("X"); 
		log.info("X");
	}
	
	
	@Test(priority=2)
	public void classicCRMlnkTest() {
		
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		log.info("$$$$$$$$$$$$$$$$$$$$$                 classicCRMlnkTest        $$$$$$$$$$$$$$$$$$$$$$$$$");
		//log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		
		boolean flag = loginPage.validateClassicCRMLnk();
		
		// Let us write assertion here: It is used for validating True / False	
		Assert.assertTrue(flag);
		
		System.out.println("LoginPageTest Class: classicCRMlnkTest method: Assertion Completed: " + flag);
		
		
		log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");		 
		log.info("X");
		log.info("X");
		log.info("X"); 
		log.info("X");
	}
	
	
	@Test(priority=3)
	public void loginTest() {
		
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		log.info("$$$$$$$$$$$$$$$$$$$$$                  Login Test              $$$$$$$$$$$$$$$$$$$$$$$$$");
		log.info("****************************************************************************************");
		log.info("****************************************************************************************");
		
		// This method is returning an object of HomePage class hence, we can store it in homPage object.
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		System.out.println("LoginPageTest Class: loginTest method: Username : " + prop.getProperty("username") + " | Password : " + prop.getProperty("password"));
		
		
		log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");		 
		log.info("X");
		log.info("X");
		log.info("X"); 
		log.info("X");
	}

		
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		System.out.println("<========================== Teardown Completed: Quit the driver and close all associated windows ==========================>");
		
		
		log.info("*******************************************************************************************************"); 
		log.info("XXXXXXXXXXXXXXXXXXXXXXX                "+"Teardown Completed "+"                 XXXXXXXXXXXXXXXXXXXXXX");		 
		log.info("*******************************************************************************************************"); 

		
	}
} // End of LoginPageTest Class


