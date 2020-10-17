package com.qa.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.flipkart.base.TestBase;
import com.qa.flipkart.util.TestUtil;

import io.qameta.allure.Step;

public class CartPage extends TestBase {

	public CartPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='_5wt5ag']//div[2]//div[1]//div[1]//div[1]//div[1]//a[1]")
	public WebElement firstProduct;
	
	@FindBy(xpath = "(//div[@class='_3gijNv col-12-12']//child::div[@class='_3ycxrs _2Rwa71']"
			+ "//child::div[1]//span[@class='pMSy0p XU9vZa'])[1]")
	public WebElement firstPrice;
	
	@FindBy(linkText = "Havells glydo 1000 W Dry Iron")
	public WebElement secondProduct;
	
	@FindBy(xpath = "(//div[@class='_3gijNv col-12-12']//child::div[@class='_3ycxrs _2Rwa71']"
			+ "//child::div[1]//span[@class='pMSy0p XU9vZa'])[2]")
	public WebElement secondPrice;
	
	@FindBy(linkText = "Aquaguard Enhance 7 L RO + UV + UF + TDS Water Purifier")
	public WebElement thirdProduct;
	
	@FindBy(xpath = "(//div[@class='_3gijNv col-12-12']//child::div[@class='_3ycxrs _2Rwa71']"
			+ "//child::div[1]//span[@class='pMSy0p XU9vZa'])[3]")
	public WebElement thirdPrice;
	
	@FindBy(xpath = "//a[@class='_3ko_Ud']//child::span")
	WebElement cart;
	
	@FindBy(xpath = "//div[contains(text(),'My Account')]")
	public WebElement user;
	
    @Step("Get the data of the final cart in an excel sheet")	
	public void getOutputData() {
		//TestUtil.stalenessExplicitWait(firstProduct);
		TestUtil.explicitWait(firstProduct);
		String firstItem = firstProduct.getText();
		//TestUtil.stalenessExplicitWait(secondProduct);
		TestUtil.explicitWait(secondProduct);
		String secondItem = secondProduct.getText();
		//TestUtil.stalenessExplicitWait(thirdProduct);
		TestUtil.explicitWait(thirdProduct);
		String thirdItem = thirdProduct.getText();
		
		
		
		String itemFirstPrice = firstPrice.getText();
		String itemSecondPrice = secondPrice.getText();
		String itemThirdPrice = thirdPrice.getText();

		String [][] itemArray = {{firstItem,itemFirstPrice},{secondItem,itemSecondPrice},{thirdItem,itemThirdPrice}};

		TestUtil.writeExcel(itemArray);
		
	}
	@Step("Locate and click on cart icon")
	public void getCart() {
		
		//TestUtil.stalenessExplicitWait(cart);
		TestUtil.explicitWait(user);
		/*try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		cart.click();
		
	}
}
