package com.qtpselenium.base;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;





import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qtpselenium.util.ErrorUtil;
import com.qtpselenium.util.Xls_Reader;


public class TestBase {

	public static Logger APP_LOGS=null;
	public static Properties CONFIG=null;	
	public static Properties OR=null;	
	public static Xls_Reader suiteXls=null;
	public static Xls_Reader suite_shop_Xls=null;
	public static Xls_Reader suite_cart_Xls=null;
	public static Xls_Reader suite_productDisplay_Xls=null;
	public static boolean isInitialized=false;
	public static WebDriver driver=null;
	public static boolean isBrowserOpened=false;
	public static boolean isLogined=false;
	public static Hashtable<String, String> sessionData = new Hashtable<String, String>();
	
	
	public void initialize() throws Exception{
		//logs
		if(! isInitialized){
		APP_LOGS = Logger.getLogger("devpinoyLogger");
		
		//config
		APP_LOGS.debug("Loading Property Files");
		CONFIG = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\config\\config.properties");
		CONFIG.load(ip);
		
		OR = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\config\\OR.properties");
		OR.load(ip);
		APP_LOGS.debug("Loaded Property Files successfully");
		APP_LOGS.debug("Loading XLS Files");
		
		//xls file
		suiteXls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\Suite.xlsx");
		suite_shop_Xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\Shop Suite.xlsx");
		suite_cart_Xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\Cart Suite.xlsx");
		suite_productDisplay_Xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\Product Display Suite.xlsx");
		APP_LOGS.debug("Loaded XLS Files");
		isInitialized=true;
		}
	}
	
	//initialize selenium RC/ WebDriver
	public void openBrowser(){
		if (!isBrowserOpened){
			if(CONFIG.getProperty("browserType").equals("Firefox")) 
				driver = new FirefoxDriver();
			else if (CONFIG.getProperty("browserType").equals("IE")) 
				driver = new InternetExplorerDriver();
			else if (CONFIG.getProperty("browserType").equals("Chrome")) 
				driver = new ChromeDriver();
		
			isBrowserOpened=true;
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		}	
	}
	
	
	public void closeBrowser(){
		driver.quit();
		isBrowserOpened=false;
	}
	
	public boolean compareTitle(String expectedResult){
		try{
			Assert.assertEquals(driver.getTitle(), expectedResult);
			}catch(Throwable t){
				ErrorUtil.addVerificationFailure(t);
				APP_LOGS.debug("Title didn't match");
				return false;
			}
		return true;
	}
	
	public boolean compareNumbers(int actualValue, int expectedValue){
		try{
			Assert.assertEquals(actualValue, expectedValue);
			}catch(Throwable t){
				ErrorUtil.addVerificationFailure(t);
				APP_LOGS.debug("Numbers didn't match");
				return false;
			}
		return true;
	}
	
	public boolean isElementPresent(String xpath){
		int count = driver.findElements(By.xpath(CONFIG.getProperty(xpath))).size();
		try{
			Assert.assertTrue(count>0, "No element present");
		}catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
			APP_LOGS.debug("element not present");
			return false;
		}
		return true;
	}
	
	public void selectProductOption(WebElement DroplistElement, String option){
		Select s = new Select(DroplistElement);
		s.selectByVisibleText(option);		
	}
	
	
	public void getItemProperty(WebElement propertyElement, String propertyName){
		List<WebElement> propertiesList = propertyElement.findElements(By.tagName("li"));
		for(int i=0;i<propertiesList.size();i++){
			if (propertiesList.get(i).getText().equals(propertyName)){
				propertiesList.get(i).click();
				break;
			}
		}
	}
	
	
	public WebElement getObject(String xpath){
		try{
			return driver.findElement(By.xpath(OR.getProperty(xpath)));
		}catch(Throwable t){
			APP_LOGS.debug("Cannot find webElement by xpath - "+ xpath);
			return null;
		}
		
	}
	
	
	public void login(){
		if (!isLogined){
			driver.findElement(By.xpath(OR.getProperty("UserNameXpath"))).sendKeys("demo");
			driver.findElement(By.xpath(OR.getProperty("PasswordXpath"))).sendKeys("demo");
			driver.findElement(By.xpath(OR.getProperty("loginButton"))).click();
			isLogined=true;
		}
	}
	
	
	public void captureScreenshot(String filename) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile , new File(System.getProperty("user.dir")+"\\src\\screenshot"+filename+".jpg"));
	}
		
}
