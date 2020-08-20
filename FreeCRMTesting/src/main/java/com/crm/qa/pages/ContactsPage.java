/*================================
* @ Author: Jairaj Kuriackose
* @Page Class: ContactsPage
=================================*/
package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactsPage extends TestBase{

	//Page Factory - OR:	
	@FindBy(xpath = "//div[text()='Contacts']")
	WebElement contactsLabel;
	
	@FindBy(xpath = "//button[contains(text(),'New')]")
	WebElement newContactBtn;
	
	@FindBy(xpath = "//div[text()='Create New Contact']")
	WebElement createNewContactLabel;
	
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(xpath="//div[@name='company']//input[@class='search']")
	WebElement company;
	
	
	@FindBy(xpath="//input[@placeholder='Email address'][@type='text']")
	WebElement email;
	
	@FindBy(xpath = "//button[@class='ui linkedin button']")
	WebElement saveBtn;
	
	
	
	// Initializing the Page Objects:
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions & Operations:
	public boolean verifyContactsLabel(){
		return contactsLabel.isDisplayed();
	}
	
	
	//Actions & Operations:
	public String verifyContactsLabelVal(){
		return contactsLabel.getText();
	}
	
	//Actions & Operations:
	public String verifyCreateNewContactLabel(){
		return createNewContactLabel.getText();
	}
	
	
	//We need to click on "New" button on contacts page
	public void createNewContactbtn(){
		newContactBtn.click();
	}
	
	
	
//	// Here you have a method / action with web element path. We will not use Pagefactory.
//	// This for selecting checkbox
//	public void selectContactsByName(String name){
//		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
//				+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
//	}
	
	
	
	// Here you have a method / action with web element path. We will not use Pagefactory.
	// This for selecting checkbox by the name
	public void selectContactschkboxByName(String names){
		
		driver.findElement(By.xpath("//td[contains(text(),'"+ names +"')]")).click();
		//td[contains(text(),'Bill Gates')]
	}
	
	
	
	// Creating New Contact
	public void createNewContact(String ftName, String ltName, String comp, String eml){
				
		System.out.println("<======================= Inside: ContactsPage: createNewContact  =======================>");
		System.out.println("Firstname : " +ftName+ " | Lastname : " +ltName+ " | Company : " +comp+ " | Email : " + eml );     //" | Category : " + caty);
		//Select select = new Select(driver.findElement(By.xpath("//div[@class='active selected item']//span[@class='text'][contains(text(),'"+caty+"')]")));
		//select.selectByVisibleText(caty);
		
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		company.sendKeys(comp);
		email.sendKeys(eml);
		saveBtn.click();
		System.out.println("<======================= Save Button: Pressed  =======================>");
 
	}
		
	
}// End ContactsPage Class
