package com.qa.flipkart.test;


import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.flipkart.base.TestBase;
import com.qa.flipkart.pages.HomeAppliancesPage;
import com.qa.flipkart.pages.HomePage;
import com.relevantcodes.extentreports.ExtentReports;


import io.qameta.allure.*;


public class HomePageTest extends TestBase {

	HomePage homePage;
	HomeAppliancesPage homeAppliancesPage;
	//ExtentReports report;

	public HomePageTest() {
		super();
	}

	@BeforeTest
	public void setup() {
		initialization();
		homePage = new HomePage();
		
		
	}


	@Test(priority=1)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to check the login functionality with invalid details")
	@Story("User Login")
	public void InvalidLogin() {
		homePage.loginUser(prop.getProperty("userphone1"), prop.getProperty("password1"));
		
		Assert.assertFalse(homePage.errorMessage.isDisplayed());
	}
	
	@Test(priority=2)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to check the login functionality with valid details")
	@Story("User Login")
	public void validLogin() {
		driver.navigate().to(prop.getProperty("url"));
		homePage.loginUser(prop.getProperty("userphone2"), prop.getProperty("password2"));
		
	}
	

	@Test(priority=3)
	@Severity(SeverityLevel.CRITICAL)
	@Description("This test is to verify the HomePage title")
	@Story("Page Title of the HomePage")
	public void verifyPageTitleTest() {
		
		String title = homePage.verifyPageTitle();
		Assert.assertEquals(title, "Online Shopping Site for Mobiles, Electronics, "
				+ "Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}

	@Test(priority =4)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to check whether the user is directed to correct inventory or not")
	@Story("Redirect User to HomeAppliances page")
	public void clickOnRequiredOptionTest() {
		//closeWindowPopup();
		 homePage.clickOnRequiredOption();
	}

	@AfterTest
	public void teardown() {
		//report.flush();
		driver.quit();
	}
}
