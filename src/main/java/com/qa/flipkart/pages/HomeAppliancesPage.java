package com.qa.flipkart.pages;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.qa.flipkart.base.TestBase;
import com.qa.flipkart.util.TestUtil;

import io.qameta.allure.Step;

public class HomeAppliancesPage extends TestBase {
	
	WebElement item_Type;
	public HomeAppliancesPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[contains(text(),'Air Purifiers')]")
	public WebElement pageTitle;
	
	@FindBy(xpath = "//h1[contains(text(),'Home Appliances')]")
	public WebElement homePageTitle;
	
	@FindBy(className = "_2yAnYN")
	public WebElement secondPageTitle;
	
	//@FindBy(xpath = "//div[@title='Philips']//child::div[@class='_1p7h2j']")
	//WebElement brandName;

	@FindBy(xpath = "//div[@title='4★ & above']//child::div[@class='_1p7h2j']")
	WebElement customerRating;


	@FindBy(xpath = "//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")
	WebElement btn;

	/*@FindBy(xpath = "(//a[contains(text(),'Home Appliances')])[2]")
	WebElement backToHome;

	@FindBy(xpath = "//div[@class='_1oXuet']")
	WebElement clearFilters;*/

	@FindBy(xpath = "//a[@class='_3ko_Ud']//child::span")
	WebElement cart;

	@FindBy(className = "_2huYiT")
	public  WebElement identifier;
	
	@FindBy(className = "_1e_EAo")
	public WebElement logo;
	
	@FindBy(xpath = "//div[@class='D0YrLF']//span[contains(text(),'Filters')]")
	WebElement filterIdentifier;
	
	@Step("Verify the Page title of Current page")
	public String verifyPageTitle()
	{
		return driver.getTitle();
	}
	@Step("Select the filter with brandName {0} and itemType {1}")
	public void selectFilters(String brandName, String item) {

		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		if (!item.equalsIgnoreCase("Water purifiers"))
		{
			item_Type = driver.findElement(By.xpath("(//a[contains(text(),'"+item+"')])[2]"));
			
		}else if (item.equalsIgnoreCase("Water purifiers"))
		{
			item_Type = driver.findElement(By.xpath("(//a[contains(text(),'"+item+"')])[1]"));
		}
		
		TestUtil.scrolltoView(item_Type);
		TestUtil.explicitWait(item_Type);
		js.executeScript("arguments[0].click()",item_Type);
		
		
		WebElement brand =  driver.findElement(By.xpath("//div[@title='"+brandName+"']//child::div[@class='_1p7h2j']"));
		
		
		TestUtil.scrolltoView(brand);
		TestUtil.explicitWait(brand);
		js.executeScript("arguments[0].click()",brand);
		
		TestUtil.explicitWait(customerRating);
		TestUtil.scrolltoView(customerRating);
		js.executeScript("arguments[0].click()",customerRating);
			
	}
	//List view of items
	@Step("Select item {0} from the list view of items and add it to cart")
	public void getProductFrmList(String itemName) {
		
		if (itemName.equalsIgnoreCase("Aquaguard Enhance 7 L RO + UV + UF + TDS Water Purifier")) {
			TestUtil.stalenessExplicitWait(driver.findElement(By.xpath("//div[contains(text(),'"+itemName+"')]")));
			TestUtil.explicitWait(driver.findElement(By.xpath("//div[contains(text(),'"+itemName+"')]")));
		}
		
		
		TestUtil.explicitWait(driver.findElement(By.xpath("//div[contains(text(),'"+itemName+"')]")));
		
		driver.findElement(By.xpath("//div[contains(text(),'"+itemName+"')]")).click();

		Set<String> browserWindow = driver.getWindowHandles();
		Iterator <String> itr = browserWindow.iterator();
		
		String parentWindow = itr.next();
		String childWindow = itr.next();
		
		driver.switchTo().window(childWindow);
		
		TestUtil.explicitWait(btn);
		
		btn.click();
		TestUtil.stalenessExplicitWait(identifier);
		TestUtil.explicitWait(identifier);
		
		driver.close();
		
		driver.switchTo().window(parentWindow);
		
		
	}
	
	//Gallery view
	@Step("Select item {0} from the gallery view of items and add it to cart")
	public void getProductFrmGallery(String itemName) {
		//TestUtil.stalenessExplicitWait(driver.findElement(By.xpath("//a[contains(text(),'"+itemName+"')]")));
		TestUtil.explicitWait(driver.findElement(By.xpath("//a[contains(text(),'"+itemName+"')]")));
		
		driver.findElement(By.xpath("//a[contains(text(),'"+itemName+"')]")).click();

		

		Set<String> browserWindow = driver.getWindowHandles();
		Iterator <String> itr = browserWindow.iterator();
		
		String parentWindow = itr.next();
		String childWindow = itr.next();
		
		driver.switchTo().window(childWindow);
		
		TestUtil.explicitWait(btn);
		
		btn.click();
		TestUtil.stalenessExplicitWait(identifier);
		TestUtil.explicitWait(identifier);
		
		driver.close();
		
		driver.switchTo().window(parentWindow);

		
	}
	@Step("View the Cart details")
	public void getCartDetails() {
		TestUtil.explicitWait(cart);
		cart.click();
		TestUtil.stalenessExplicitWait(identifier);
		TestUtil.explicitWait(identifier);
	}
	//Refreshing fliters by clicking required options not working.
	@Step("Refresh the filters and the page after order")
	public void refreshPage() {
		
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
	}
	
	
}
