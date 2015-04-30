package com.qtpselenium.suiteShop;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.util.ErrorUtil;
import com.qtpselenium.util.TestUtil;

public class CheckProductDisplay extends TestSuiteBase{

	String runmodes[]=null;
	int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPassed=true;
	static int rowNum=1;
	
	@BeforeTest
	public void checkTestSkipped(){
		if(! TestUtil.isTestCaseRunnable(suite_shop_Xls, this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO"); //in log
			throw new SkipException("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO"); //in report
		}
		//load the runmodes of tests
		runmodes=TestUtil.getDataSetRunmodes(suite_shop_Xls, this.getClass().getSimpleName());
	}
	
	
	@Test
	public void checkProductDisplay(){
		count++;
		if(! runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test is set to no  "+ count); 
		}
		//in report
		APP_LOGS.debug("Executing checkProductDisplay");
		
		//selenium code
		openBrowser();
		APP_LOGS.debug("Loading site");
		driver.get(CONFIG.getProperty("testSiteName"));	
		if (! isElementPresent(OR.getProperty("shop_link")))
			fail=true;
		
		driver.findElement(By.xpath(OR.getProperty("shop_link"))).click();
		
		if (! compareTitle("Welcome to VirtueMart 3 Sample store")){
			fail=true;
			return; //optional
		}
		System.out.println(driver.getTitle());
		
		List<WebElement>itemsList = driver.findElements(By.xpath("//div[starts-with(@class,'product vm-col vm-col-3')]/div[@class='spacer']"));
		if (! compareNumbers(itemsList.size(), 9)){
			fail=true;
			//return;
		}
		System.out.println("items on the page "+itemsList.size());
		closeBrowser();
	}
	
	//report the error in xls file
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
		 rowNum = TestUtil.getTestCaseRowNumber(suite_shop_Xls,this.getClass().getSimpleName());
		if (isTestPassed)
			TestUtil.reportDataSetResult(suite_shop_Xls, "TestCases", rowNum, "PASS");
		else
			TestUtil.reportDataSetResult(suite_shop_Xls, "TestCases", rowNum, "FAIL");
		
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(suite_shop_Xls, this.getClass().getSimpleName());
	}
	
	
}
