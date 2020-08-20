package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

//import com.crm.qa.util.WebEventListener;


// initialize all properties:Methods (so that redundancy can be reduced)
// This is the parent class we will inherit properties (methods and variables) to child
// Static keywords: (variables, methods, blocks and nested classes) are memory efficient. It can be accessed globally. 
//                  They are referenced by the class name itself or reference to the Object of that class.

//_____________________________________________________________
//|Keyword    │ Class │ Package │ Subclass │ Subclass │ World  |
//|           │       │         │(same pkg)│(diff pkg)│        |
//|───────────┼───────┼─────────┼──────────┼──────────┼────────|
//|public     │   +   │    +    │    +     │     +    │   +    | 
//|───────────┼───────┼─────────┼──────────┼──────────┼────────|
//|protected  │   +   │    +    │    +     │     +    │        | 
//|───────────┼───────┼─────────┼──────────┼──────────┼────────|
//|no modifier│   +   │    +    │    +     │          │        | 
//|───────────┼───────┼─────────┼──────────┼──────────┼────────|
//|private    │   +   │         │          │          │        |
//|___________|_______|_________|__________|__________|________|
 //+ : accessible         blank : not accessible

public class TestBase {
	
	public static WebDriver driver; // initialize & Declaring: reference Variables
	public static Properties prop; // Class Variable: reference Variables
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener; // WebEvent Listener reference variable
	
	// Declaring and Initializing Logger Class
	public static Logger log= LogManager.getLogger(TestBase.class.getName());
	//public static Logger log= Logger.getLogger(LoggerExample.class); 
	
    // we will read the properties inside this constructor.
	public TestBase(){
	
		System.out.println("############################# TestBase Class: Inside TestBase Constructor #############################");
		
		try {

			prop = new Properties();
			
			// mention path of the properties file. It is inside: "com.crm.qa.config"
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\crm"+ "\\qa\\config\\config.properties");
			prop.load(ip);
		
			System.out.println("Execution Started : Reading Properties : ip variable contains value: "+ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("TestBase Class: TestBase Constructor: Property File Location: " + System.getProperty("user.dir")+ "\\src\\main\\java\\com\\crm"+ "\\qa\\config\\config.properties");			
		log.info("Logger: ========= Inside TestBase Constructor =========");
	}
	
	
	// Create Initialization Method
	// Here we will read the properties
	public static void initialization() {
		
		String browserName = prop.getProperty("browser");
		String driverPath =  prop.getProperty("driverpath");
		
		
		System.out.println("############################# TestBase Class: Inside initialization Method | Browser Name: "+ browserName + " :  Driver Path: " + driverPath);			

	
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", driverPath +"\\chromedriver.exe");		    //"C:\Users\jaikuria\Desktop\BrowserDrivers\chromedriver.exe"
			driver = new ChromeDriver(); 
			System.out.println("Webdriver Launched :  Chrome");
			log.info("Logger: ========= Chrome Driver Launched =========");
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", driverPath + "\\geckodriver.exe");	            //"C:\Users\jaikuria\Desktop\BrowserDrivers\geckodriver.exe"
			driver = new FirefoxDriver(); 
			System.out.println("Webdriver Launched :  Firefox");
			log.info("Logger: ========= Firefox Driver Launched =========");
		}
		else if(browserName.equals("IE")){
			System.setProperty("webdriver.ie.driver", driverPath + "\\IEDriverServer.exe");				//"C:\Users\jaikuria\Desktop\BrowserDrivers\IEDriverServer.exe"
			driver = new InternetExplorerDriver(); 
			System.out.println("Webdriver Launched :  IExplorer");
			log.info("Logger: ========= Internet Explorer Driver Launched =========");
		}
		
		
		// Standard Code Snippet for EventListner added here.
		e_driver = new EventFiringWebDriver(driver);
		
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		
		
		// Manage Webpage: Maximize, delete all cookies, Timeout, launch url
		
		driver.manage().window().maximize();
		System.out.println("TestBase Class: initialization: Browser Maximize");	
		driver.manage().deleteAllCookies();
		System.out.println("TestBase Class: initialization: All Cookies Deleted");	
		
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS); // Taking values directly from Util Class
		System.out.println("TestBase Class: initialization: PageLoad Timeout");	
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS); // Taking values directly from Util Class
		System.out.println("TestBase Class: initialization: Implicit Wait");	
		
		driver.get(prop.getProperty("url"));
		
		System.out.println("TestBase Class: initialization: Launched Browser: " + prop.getProperty("url") + "\n\n");	
	     //driver.navigate().to(prop.getProperty("url"));  
		
		
		
	}
	
	
	
	

}
