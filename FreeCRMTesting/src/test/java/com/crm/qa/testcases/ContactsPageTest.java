package com.crm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	
	// Class Object initialized
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	
	// Variables Declared for the sheetname of the Contacts Page Test data
	String sheetName = "contactsData";
	
	// ContactsPage Constructor with super() keyword (shortcut to import all dependent class: Ctrl + Shift + O)  
	public ContactsPageTest(){
	
		// Super() class call will initialize the properties
		super();
		System.out.println("Inside ContactsPageTest constructor");
	}
	
	
	
	//Test cases should be independent of each other. Hence, launching, login, closing browser for each test cases is a good practice.
	// TestNG Annotations
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		System.out.println("ContactsPageTest Class: Inside Setup() method");
		
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		
		// Login to homepage
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		//Since, We will be on Homepage we need to click on "ContactsLink" to navigate to contacts page
		contactsPage = homePage.clickOnContactsLink();
		
		//TestUtil.runTimeInfo("error", "login successful");
		//testUtil.switchToFrame();
	}
	
	
	// Verify Contacts Label is present.
	@Test(priority=1)
	public void verifyContactsPageLabel(){
		//driver.navigate().refresh();
		//driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); 
		System.out.println("<======================= Test: verifyContactsPageLabel =======================>");
		//Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page"); // verify if Contacts label is displayed
		String ContactPagedisplayed = contactsPage.verifyContactsLabelVal();
		Assert.assertEquals(ContactPagedisplayed, "Contacts","Contacts Page is not displayed"); // Validate the displayed value
		System.out.println("ContactPageTest Class : verifyContactsPageLabel: " + ContactPagedisplayed);
	}
	

	// Verify Single Contact Selection.
	@Test(priority=2)
	public void selectSingleContactsTest(){
		driver.navigate().refresh();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); 
		System.out.println("<======================= Test: verifyselectSingleContactsTest =======================>");
		//contactsPage.selectContactsByName("test2 test2");
		contactsPage.selectContactschkboxByName("Bill Gates");
	}

	

	// Verify Multiple Contact Selection.
	@Test(priority=3)
	public void selectMultipleContactsTest(){
		driver.navigate().refresh();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); 
		System.out.println("<======================= Test: verifyselectMultipleContactsTest =======================>");
		contactsPage.selectContactschkboxByName("Steve Jobs");
		contactsPage.selectContactschkboxByName("Bill Gates");
	}
	
	
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data; // 2 dimensional array
	}
	
	
	// For the below test the data provider is getCRMTestData
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String FirstName, String LastName, String Company, String EmailAddress){
		
		// This method will click on the New Button (contacts Page)
		System.out.println("<======================= Test: validateCreateNewContact =======================>");
		contactsPage.createNewContactbtn();
		
		System.out.println("<======================= Test: verifyCreateNewContactLabel =======================>");
		driver.navigate().refresh();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); 
		String CreateContactdisplayed = contactsPage.verifyCreateNewContactLabel();
		//Assert.assertEquals(CreateContactdisplayed, "Create New Contact","Create new Contacts is not displayed on the Contact Creation"); // Validate the displayed value
		System.out.println("ContactPageTest Class : verifyCreateNewContactLabel: " + CreateContactdisplayed);
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		System.out.println("<======================= Test: createNewContact: Add Records =======================>");
		//contactsPage.createNewContact("Tom", "Cruise", "Dreamworks", "tom.cruise@dreamworks.com"); //, "Customer");
		contactsPage.createNewContact(FirstName, LastName, Company, EmailAddress);
	}



	
	

	@AfterMethod
	public void tearDown(){
		//driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.quit();
		System.out.println("********** ContactsPageTest: Teardown Completed: Quit the driver and close all associated windows **********");
	}
	

}
