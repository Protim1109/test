package com.qa.flipkart.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.flipkart.base.TestBase;

import io.qameta.allure.Attachment;

public class TestUtil extends TestBase {

	public static long Implicit_Wait_Time = 30;
	public static long Page_Load_Time = 30;
	public static XSSFWorkbook wb ;
	public static XSSFSheet sheet;
	
	public static void scrolltoView(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()",element);

	}

	public static void explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void stalenessExplicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.stalenessOf(element));
	}
	
	@Attachment
	public static String getScreenshot(WebDriver driver) {
		
		String timestamp = new SimpleDateFormat("dd-MM-yyyy.HH.mm.ss").format(new Date());
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		
		
		try {
			FileUtils.copyFile(file,new File("C:\\Users\\anuplab\\Desktop\\JAVA PROGRAMMES\\Flipkart\\Screenshots\\Screenshot"+timestamp+".jpg"));
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String path = "C:\\Users\\anuplab\\Desktop\\JAVA PROGRAMMES\\Flipkart\\Screenshots\\Screenshot"+timestamp+".jpg";
		return path;
	}
	
	
	public static Object[][] readExcel() {
		
		File file = new File("src\\main\\java\\com\\qa\\flipkart\\testdata\\TestData.xlsx");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			
			 wb = new XSSFWorkbook(fis);
			
			 sheet = wb.getSheet("InputData");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i=0;i<sheet.getLastRowNum();i++)
		{
			for (int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		
		return data;
		
	}
	
	public static void writeExcel(String items[][]) {
		
		File file = new File("src\\main\\java\\com\\qa\\flipkart\\testdata\\OutputTestData.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook();
		
		XSSFSheet sheet = wb.createSheet("OutputData");
		Row row = sheet.createRow(0);
		
		//Header 
		
		Cell cell11 = row.createCell(0);
		cell11.setCellValue("ItemName");
		Cell cell12 = row.createCell(1);
		cell12.setCellValue("Price");
		
		
		
		
		
		//Values
		
		for (int j=0;j<3;j++) {
			
			Row row2 = sheet.createRow(j+1);
			
			for (int k=0;k<2;k++) {
				
				row2.createCell(k).setCellValue(items[j][k]);
			}
			
		}
		
		 
		try {
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
