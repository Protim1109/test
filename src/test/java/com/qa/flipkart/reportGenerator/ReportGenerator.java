package com.qa.flipkart.reportGenerator;


import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.flipkart.base.TestBase;
import com.qa.flipkart.test.TestRunner;
import com.qa.flipkart.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.qameta.allure.Attachment;

public class ReportGenerator extends TestBase implements ITestListener{

	ExtentReports report;
	ExtentTest test;
	Logger log;
	@Override
	public void onTestStart(ITestResult result) {
		test = report.startTest(result.getName()+" test");
		test.log(LogStatus.INFO, "The test "+result.getName()+" started executing");
		log.info("The test "+result.getName()+" got started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "The test "+result.getName()+" got passed");
		log.info("The test "+result.getName()+" got passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(LogStatus.FAIL, "The test "+result.getName()+" got failed");
		
		log.info("The test "+result.getName()+" got failed");
		
		String screenshotSrc = TestUtil.getScreenshot(driver);
		test.log(LogStatus.FAIL, test.addScreenCapture(screenshotSrc));
		test.log(LogStatus.FAIL, result.getThrowable());
		
		//attach allure failed screenshot
		saveFailureScreenShot(driver);
	}
	
	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver)
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "The test "+result.getName()+" got skipped");
		
		log.info("The result "+result.getName()+" got skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		report = new ExtentReports("C:\\Users\\anuplab\\Desktop\\JAVA PROGRAMMES\\Flipkart\\test-output\\ExtentReport.html", true);
		log = Logger.getLogger(TestRunner.class);
	}

	@Override
	public void onFinish(ITestContext context) {
		
		report.endTest(test);
		report.flush();
		
	}

	
}
