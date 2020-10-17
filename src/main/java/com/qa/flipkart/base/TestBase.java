package com.qa.flipkart.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



import com.qa.flipkart.util.TestUtil;

public class TestBase{

	public static WebDriver driver;
	public static Properties prop;

	public TestBase() {
		try {

			prop = new Properties();

			FileInputStream fis = new FileInputStream("src\\main\\java\\com\\qa\\flipkart\\config\\config.properties");

			prop.load(fis);

		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

		public static void initialization() {
			String url = prop.getProperty("url");
			String browser = prop.getProperty("browser");
			
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			//driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_Time, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Wait_Time, TimeUnit.SECONDS);
			
			driver.get(url);
		}
		
		
}