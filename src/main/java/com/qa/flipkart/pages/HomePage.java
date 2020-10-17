package com.qa.flipkart.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.flipkart.base.TestBase;
import com.qa.flipkart.util.TestUtil;

import io.qameta.allure.Step;

public class HomePage extends TestBase{

	public HomePage() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@class='_2AkmmA _29YdH8']")
	WebElement closePopUpBtn;
	
	@FindBy(xpath = "//span[@class='ZAtlA-']//span")
	public WebElement errorMessage;

	@FindBy(xpath = "//span[contains(text(),'TVs & Appliances')]")

	public WebElement mainOption;

	@FindBy(xpath = "//li[@class='_2GG4xt']//ul//child::li[1]//a[@title='Small Home Appliances']")

	WebElement smallHomeApplianceOption;
	
	@FindBy(xpath="//h1[contains(text(),'Home Appliances')]")
	WebElement verifyPage;

	@FindBy(xpath = "//input[@class='_2zrpKA _1dBPDZ']")
	public WebElement email;
	
	@FindBy(xpath = "//input[@class='_2zrpKA _3v41xv _1dBPDZ']")
	public WebElement password;
	
	@FindBy(xpath = "//button[@class='_2AkmmA _1LctnI _7UHT_c']")
	WebElement loginbtn;
	
	@FindBy(xpath = "//div[@id='container']/div/div[@class='zi6sUf']/div[@class='_3zdbog _3Ed3Ub']/ul[@class='_114Zhd']/li[2]/ul[1]")
	WebElement list;
	
	@FindBy(className = "_1e_EAo")
	public WebElement logo;
	
	@FindBy(xpath = "//div[contains(text(),'My Account')]")
	public WebElement user;
	
	@Step("Verify Login using User phone {0} and password {1}")
	public void loginUser(String userphone,String pass) {
	email.sendKeys(userphone);	
	password.sendKeys(pass);
	loginbtn.click();
	}
	
	public void closePopup() {
		if (closePopUpBtn.isDisplayed())
			closePopUpBtn.click();
	}
	@Step("Verify the HomePage title")
	public String verifyPageTitle() {

		return driver.getTitle();
	}
	
	@Step("Visit the required inventory using the correct option")
	public void clickOnRequiredOption() {

		
		
		
		TestUtil.explicitWait(user);
		
		Actions action = new Actions(driver);
		
		action.moveToElement(mainOption).build().perform();
		
		TestUtil.explicitWait(list);
	
		action.moveToElement(smallHomeApplianceOption).build().perform();
		action.click().build().perform();
		
		
		TestUtil.explicitWait(verifyPage);
		
		
		
	}


}
