package com.crm.qa.ExtentReportListener;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

// Standard Template given by Extent Report 
public class ExtentReportListener implements IReporter {
	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		
		// You can update the project result name below
		extent = new ExtentReports(outputDirectory + File.separator
				+ "Extent.html", true);

		//Iterating over each suite included in the test
		for (ISuite suite : suites) {
			
	         //Following code gets the suite name
	         String suiteName = suite.getName();
	         
	         
			 //Getting the results for the said suite
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				
				// Additional Code added by Jairaj
			    System.out.println("Executed Test Automation Suite: ("+ suiteName+ ")\n\n");
				System.out.println("Passed tests Cases: " + context.getPassedTests().getAllResults().size());
			    System.out.println("Failed tests Cases: " + context.getFailedTests().getAllResults().size());
			    System.out.println("Skipped tests Cases: " +context.getSkippedTests().getAllResults().size()+ "\n\n");
			    System.out.println("===============================================================================");
			    
				// Standard TestNG Code
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				       
			}
		}

		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
				}

				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}