package com.qa.flipkart.test;

import org.testng.annotations.*;

import com.qa.flipkart.base.TestBase;
import com.qa.flipkart.pages.CartPage;
import com.qa.flipkart.pages.HomeAppliancesPage;
import com.qa.flipkart.pages.HomePage;

import io.qameta.allure.*;



public class CartPageTest extends TestBase{

	HomeAppliancesPage homeAppliancesPage;
	CartPage cartPage;
	HomePage homePage;
	
	public CartPageTest() {
		super();
	}
	
	@BeforeClass
	public void setup() {
		initialization();
		
		homePage = new HomePage();
		
		homePage.loginUser(prop.getProperty("userphone2"), prop.getProperty("password2"));
		
		cartPage = new CartPage();
		
		cartPage.getCart();
	}
	
	@Test
	@Severity(SeverityLevel.BLOCKER)
	@Description("Get the details of the final cart of the user")
	@Story("Get the details of the user's cart")
	public void getFinalResult() {
		cartPage.getCart();
		cartPage.getOutputData();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
