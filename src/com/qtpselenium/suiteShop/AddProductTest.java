package com.qtpselenium.suiteShop;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.util.TestUtil;

public class AddProductTest extends TestSuiteBase{
	String runmodes[]=null;
	int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPassed=true;

	@BeforeTest
	public void checkTestSkipped(){
		if(! TestUtil.isTestCaseRunnable(suite_shop_Xls, this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO");
			throw new SkipException("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO");
		}
		runmodes=TestUtil.getDataSetRunmodes(suite_shop_Xls, this.getClass().getSimpleName());
	}
	
	@Test(dataProvider="getTestData")
	public void addProductTest(
			String ProductName, 
			String Clothing_weave, 
			String Clothing_size, 
			String Quantity) throws InterruptedException, IOException{
		count++;
		if(! runmodes[count].equalsIgnoreCase("Y"))
			throw new SkipException("Runmode for test is set to no  "+ count); 
		APP_LOGS.debug("Executing testCaseA2");
		APP_LOGS.debug(ProductName+" - "+Clothing_weave+" - "+Clothing_size+" - "+Quantity);
		
		sessionData.put("item_"+count, ProductName);
		
		openBrowser();
		driver.manage().window().maximize();
		driver.get(CONFIG.getProperty("testSiteName"));
		login();
		Thread.sleep(2000);
		driver.findElement(By.xpath(OR.getProperty("VM_SearchInShop_link"))).click();
		
		//getObject("searchField_link").sendKeys(ProductName);
		driver.findElement(By.xpath(OR.getProperty("searchField_link"))).sendKeys(ProductName);
		driver.findElement(By.xpath(OR.getProperty("searchField_link"))).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//getObject("firstProductClick").click();
		driver.findElement(By.xpath(OR.getProperty("firstProductClick"))).click();
		Thread.sleep(4000);
		
		if (!Clothing_weave.equals("") || !Clothing_size.equals("")){
			Actions act = new Actions(driver);
			WebElement elem = driver.findElement(By.xpath(OR.getProperty("openWeaveMenuXpath")));
			act.moveToElement(elem).build().perform();
			act.click(elem).build().perform();
			WebElement WeaveWebElement = driver.findElement(By.xpath(OR.getProperty("WeaveWebElement")));
			getItemProperty(WeaveWebElement, Clothing_weave);
	
			elem = driver.findElement(By.xpath(OR.getProperty("openSizeMenuXpath")));
			act.moveToElement(elem).build().perform();
			act.click(elem).build().perform();
			WebElement SizeWebElement = driver.findElement(By.xpath(OR.getProperty("SizeWebElement")));
			getItemProperty(SizeWebElement, Clothing_weave);
		}
		Actions act = new Actions(driver);
		WebElement elem = driver.findElement(By.xpath("//form[@class='product js-recalculate']/div[@class='addtocart-bar']"));
		act.moveToElement(elem).build().perform();
		elem=driver.findElement(By.xpath("//form[@class='product js-recalculate']/div[@class='addtocart-bar']/span[3]/input"));
		act.clickAndHold(elem).build().perform();
		Thread.sleep(2000);
		act.release().build().perform();
		//driver.findElement(By.xpath("//form/div/span[3]/input[@type='submit']")).click();
		
		captureScreenshot(this.getClass().getSimpleName()+"_"+count);
		
		System.out.println("*****"+getObject("commitBox").getText());
	}
	
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(suite_shop_Xls, this.getClass().getSimpleName());
	}
	
	
	@AfterMethod
	public void setDataResult(){
		if (skip){
			System.out.println("****SKIP*****"+count);
			TestUtil.reportDataSetResult(suite_shop_Xls, this.getClass().getSimpleName(), count+2, "SKIP");
		}else if (fail){
			isTestPassed=false;
			System.out.println("****FAIL*****"+count);
			TestUtil.reportDataSetResult(suite_shop_Xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}else{
			System.out.println("***PASS****"+count);
			TestUtil.reportDataSetResult(suite_shop_Xls, this.getClass().getSimpleName(), count+2, "PASS");
		}
		skip=false;
		fail=false;
	}
	
	
	@AfterTest
	public void reportTestResult(){
		if (isTestPassed)
			TestUtil.reportDataSetResult(suite_shop_Xls, "TestCases", TestUtil.getTestCaseRowNumber(suite_shop_Xls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(suite_shop_Xls, "TestCases", TestUtil.getTestCaseRowNumber(suite_shop_Xls,this.getClass().getSimpleName()), "FAIL");
		
	}
	
	
}
