package com.qa.flipkart.test;


import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.flipkart.base.TestBase;
import com.qa.flipkart.pages.HomeAppliancesPage;
import com.qa.flipkart.pages.HomePage;
import com.qa.flipkart.util.TestUtil;

import io.qameta.allure.*;


public class HomeAppliancesPageTest extends TestBase {
	
	HomePage homePage;
	HomeAppliancesPage homeAppliancesPage;
	Object[][] data;
	public HomeAppliancesPageTest() {
		super();
	}

	@BeforeClass
	public void setup()
	{
		initialization();
		homePage = new HomePage();
		homeAppliancesPage = new HomeAppliancesPage();
		homePage.loginUser(prop.getProperty("userphone2"),prop.getProperty("password2"));
		
		homePage.clickOnRequiredOption();
		
		data = TestUtil.readExcel();

	}

	@Test
	@Severity(SeverityLevel.CRITICAL)
	@Description("This test is to verify the HomeApplication page Title")
	@Story("HomeAppliance page title")
	public void titleVerificationTest() {
		String title = homeAppliancesPage.verifyPageTitle();
		Assert.assertEquals(title, "Home Appliances Store - "
				+ "Buy Small Home Appliances Flat 35% Off Online | Flipkart.com");
	}

	@Test
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select all the filters required for selecting the first product")
	@Story("Select all the filters")
	public void firstProductFilter() {

		homeAppliancesPage.selectFilters(data[0][0].toString(),data[0][1].toString());
	}

	@Test
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select the product and add it to cart")
	@Story("Select the product and add it to cart")
	public void selectFirstProduct() {

		homeAppliancesPage.getProductFrmList(data[0][2].toString());

	}

	@Test
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test refresh the page and filters to allow following orders")
	@Story("Refresh the filters and the page")
	public void refreshSearchResult() {

		TestUtil.explicitWait(homeAppliancesPage.pageTitle);

		homeAppliancesPage.refreshPage();

	}

	@Test
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select all the filters required for the second product")
	@Story("Select all the filters")
	public void secondProductFilter() {
		
		TestUtil.explicitWait(homeAppliancesPage.homePageTitle);
		homeAppliancesPage.selectFilters(data[1][0].toString(), data[1][1].toString());
	}

	@Test
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select the second product and add it to cart")
	@Story("Select the product and add it to cart")
	public void selectSecondProduct() {

		homeAppliancesPage.getProductFrmGallery(data[1][2].toString());


	}

	@Test
	@Severity(SeverityLevel.CRITICAL)
	@Description("This test is to get a view of the cart with orders")
	@Story("Get a view of the cart with orders so far")
	public void getTemporaryResult() {

		homeAppliancesPage.getCartDetails();

		
			TestUtil.getScreenshot(driver);
		


		Assert.assertEquals(homeAppliancesPage.verifyPageTitle(),"Shopping Cart | Flipkart.com");	

	}
	@Test
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

	@Test
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to select all the filters required for selecting the third product")
	@Story("Select the product and add it to cart")
	public void selectThirdProduct() {

		homeAppliancesPage.getProductFrmList(data[2][2].toString());
	}

	@Test
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test is to get a view of the cart with orders")
	@Story("Get the view of the cart with final list of orders")
	public void getFinalCartDetails() {
		
		homeAppliancesPage.getCartDetails();

		
			TestUtil.getScreenshot(driver);
		

		Assert.assertEquals(homeAppliancesPage.verifyPageTitle(),"Shopping Cart | Flipkart.com");
	}
	
	@AfterClass
	public void tearDown() {

		driver.quit();
	}
}
