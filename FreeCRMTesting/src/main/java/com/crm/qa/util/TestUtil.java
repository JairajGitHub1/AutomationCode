package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;


//Test Util must extend TestBase Class
public class TestUtil extends TestBase {
	
	// Create 2 static variable
  	//===============================================	
	public static long PAGE_LOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 20;
  	
	//===============================================
	// Static string created for excel file
	public static String TESTDATA_SHEET_PATH = "C:\\Eclipse_Java_Workspace\\FreeCRMTesting\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCrmTestData.xlsx";

			  
  	static Workbook book;
  	static Sheet sheet;
  	static JavascriptExecutor js;
  	
  	  
//  	===============================================
//  	 Switch to Specific Frame Method
//  	===============================================
//  	public void switchToFrame() {
//  		driver.switchTo().frame("mainpanel");
//  		//driver.switchTo().frame(FrameName);
//  	}
  	
  	
  	//===============================================
  	// Switch to Any Frame Method
  	//===============================================
  	public void switchToAnyFrame(String FrameName) {

  		driver.switchTo().frame(FrameName);
  	}

  	
	
	//===============================================	
	// Get Test Data Method for reading excel //String sheetName
	//===============================================
	public static Object[][] getTestData(String sheetName) {
		
		FileInputStream file = null;
		
		try {
			
			//file object will read the sheet from the given path
			file = new FileInputStream(TESTDATA_SHEET_PATH);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		// try with multiple catch
		try {
			
			book = WorkbookFactory.create(file);			
			
		} catch (IOException  e) {
			e.printStackTrace();
		}
		
		
		//} catch (IOException | EncryptedDocumentException | InvalidFormatException e) 
		
		// Get the sheet (sheetName) within the workbook
		sheet = book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		System.out.println("==================== Reading Sheet : " + sheetName +"====================");
		System.out.println("Total Rows: " + sheet.getLastRowNum() + "Total Columns: " + sheet.getRow(0).getLastCellNum());
		
		// Loop For Row iteration
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			
			// Loop For Column iteration
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				// Object Array "data" store all the values
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				System.out.println("# Row | Col : "+ i + " : " + k + " ==> " +data[i][k]);
			}
		}
		// return the array data
		return data;
	}
  	
  	
  	
  	
  	//===============================================	
  	// Take Screenshot Method
  	//===============================================

  	public static void takeScreenshotAtEndOfTest() throws IOException {
  		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  		String currentDir = System.getProperty("user.dir");
  		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
  	}

  	
  	
  	
  	//===============================================	
  	// Runtime Info Method
  	//===============================================
  	public static void runTimeInfo(String messageType, String message) throws InterruptedException {
  		js = (JavascriptExecutor) driver;
  		// Check for jQuery on the page, add it if need be
  		js.executeScript("if (!window.jQuery) {"
  				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
  				+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
  				+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
  		Thread.sleep(5000);

  		// Use jQuery to add jquery-growl to the page
  		js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

  		// Use jQuery to add jquery-growl styles to the page
  		js.executeScript("$('head').append('<link rel=\"stylesheet\" "
  				+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
  		Thread.sleep(5000);

  		// jquery-growl w/ no frills
  		js.executeScript("$.growl({ title: 'GET', message: '/' });");
  //'"+color+"'"
  		if (messageType.equals("error")) {
  			js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });");
  		}else if(messageType.equals("info")){
  			js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
  		}else if(messageType.equals("warning")){
  			js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
  		}else
  			System.out.println("no error message");
  		// jquery-growl w/ colorized output
//	  		js.executeScript("$.growl.error({ title: 'ERROR', message: 'your error message goes here' });");
//	  		js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
//	  		js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
  		Thread.sleep(5000);
  	}
  
  
  //=======================================================================
  //=======================================================================
  
  
	  

	 
}
