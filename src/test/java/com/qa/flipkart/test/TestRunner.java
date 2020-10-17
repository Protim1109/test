package com.qa.flipkart.test;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.flipkart.base.TestBase;
import com.qa.flipkart.pages.CartPage;
import com.qa.flipkart.pages.HomeAppliancesPage;
import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.util.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class TestRunner extends TestBase{
	
	HomePage homePage;
	HomeAppliancesPage homeAppliancesPage;
	CartPage cartPage;
	Object[][] data;
	
	public TestRunner() {
		
		super();
	}
	
	@BeforeClass
	public void setup() {
		initialization();
		homePage = new HomePage();
		homeAppliancesPage = new HomeAppliancesPage();
		cartPage = new CartPage();
		
		data = TestUtil.readExcel();
		
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
		
		homePage.clickOnRequiredOption();
	}
	
	@Test(priority = 5)
	@Severity(SeverityLevel.CRITICAL)
	@Description("This test is to verify the HomeApplication page Title")
	@Story("HomeAppliance page title")
	public void titleVerificationTest() {
		String title = homeAppliancesPage.verifyPageTitle();
		Assert.assertEquals(title, "Home Appliances Store - "
				+ "Buy Small Home Appliances Flat 35% Off Online | Flipkart.com");
	}

	@Test(priority = 6)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select all the filters required for selecting the first product")
	@Story("Select all the filters")
	public void firstProductFilter() {

		homeAppliancesPage.selectFilters(data[0][0].toString(),data[0][1].toString());
	}
	
	@Test(priority = 7)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select the product and add it to cart")
	@Story("Select the product and add it to cart")
	public void selectFirstProduct() {

		homeAppliancesPage.getProductFrmList(data[0][2].toString());

	}
	
	@Test(priority = 8)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test refresh the page and filters to allow following orders")
	@Story("Refresh the filters and the page")
	public void refreshSearchResult() {

		TestUtil.explicitWait(homeAppliancesPage.pageTitle);

		homeAppliancesPage.refreshPage();

	}
	
	@Test(priority = 9)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select all the filters required for the second product")
	@Story("Select all the filters")
	public void secondProductFilter() {
		
		TestUtil.explicitWait(homeAppliancesPage.homePageTitle);
		homeAppliancesPage.selectFilters(data[1][0].toString(), data[1][1].toString());
	}

	@Test(priority = 10)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select the second product and add it to cart")
	@Story("Select the product and add it to cart")
	public void selectSecondProduct() {

		homeAppliancesPage.getProductFrmGallery(data[1][2].toString());


	}
	
	@Test(priority = 11)
	@Severity(SeverityLevel.CRITICAL)
	@Description("This test is to get a view of the cart with orders")
	@Story("Get a view of the cart with orders so far")
	public void getTemporaryResult() {

		homeAppliancesPage.getCartDetails();

		
			TestUtil.getScreenshot(driver);
		


		Assert.assertEquals(homeAppliancesPage.verifyPageTitle(),"Shopping Cart | Flipkart.com");	

	}
	@Test(priority = 12)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select all the filters required for selecting the third product")
	@Story("Select all the filters")
	public void thirdProductFilter() {
		driver.navigate().back();
		
		TestUtil.explicitWait(homeAppliancesPage.secondPageTitle);

		homeAppliancesPage.refreshPage();
		
		TestUtil.explicitWait(homeAppliancesPage.homePageTitle);

		homeAppliancesPage.selectFilters(data[2][0].toString(),data[2][1].toString());

	}

	@Test(priority = 13)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select all the filters required for selecting the third product")
	@Story("Select the product and add it to cart")
	public void selectThirdProduct() {

		homeAppliancesPage.getProductFrmList(data[2][2].toString());
	}

	@Test(priority = 14)
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to get a view of the cart with orders")
	@Story("Get the view of the cart with final list of orders")
	public void getFinalCartDetails() {
		
		homeAppliancesPage.getCartDetails();

		
			TestUtil.getScreenshot(driver);
		

		Assert.assertEquals(homeAppliancesPage.verifyPageTitle(),"Shopping Cart | Flipkart.com");
	}
	
	@Test(priority = 15)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Get the details of the final cart of the user")
	@Story("Get the details of the user's cart")
	public void getFinalResult() {
		
		cartPage.getOutputData();
	}

	
	@AfterTest
	public void teardown() {
		
		driver.quit();
	}
}
