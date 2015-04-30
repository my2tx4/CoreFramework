package com.qtpselenium.suiteCart;



import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.suiteShop.TestSuiteBase;
import com.qtpselenium.util.TestUtil;

public class CheckTotal extends TestSuiteBase{
	String runmodes[]=null;
	int count=-1;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPassed=true;
	
	@BeforeTest
	public void checkTestSkipped(){
		if(! TestUtil.isTestCaseRunnable(suite_cart_Xls, this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO");
			throw new SkipException("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO");
		}
		runmodes=TestUtil.getDataSetRunmodes(suite_cart_Xls, this.getClass().getSimpleName());
	}
	@Test
	public void CheckTotal() throws IOException{
		count++;
		if(! runmodes[count].equalsIgnoreCase("Y"))
			throw new SkipException("Runmode for test is set to no  "+ count); 
		APP_LOGS.debug("Executing CheckTotal");
		getObject("showCartXpath").click();
		
		for(int i=0;i<sessionData.size();i++){
			System.out.println(sessionData.get("item_"+i));
		}
		
		//Assert.assertEquals();
		captureScreenshot("11");
	}
	
	
	@AfterMethod
	public void setDataResult(){
		if (skip){
			System.out.println("****SKIP*****"+count);
			TestUtil.reportDataSetResult(suite_cart_Xls, this.getClass().getSimpleName(), count+2, "SKIP");
		}else if (fail){
			isTestPassed=false;
			System.out.println("****FAIL*****"+count);
			TestUtil.reportDataSetResult(suite_cart_Xls, this.getClass().getSimpleName(), count+2, "FAIL");
		}else{
			System.out.println("***PASS****"+count);
			TestUtil.reportDataSetResult(suite_cart_Xls, this.getClass().getSimpleName(), count+2, "PASS");
		}
		skip=false;
		fail=false;
	}
	
	
	@AfterTest
	public void reportTestResult(){
		if (isTestPassed)
			TestUtil.reportDataSetResult(suite_cart_Xls, "TestCases", TestUtil.getTestCaseRowNumber(suite_cart_Xls, this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(suite_cart_Xls, "TestCases", TestUtil.getTestCaseRowNumber(suite_cart_Xls, this.getClass().getSimpleName()), "FAIL");
		
	}
	
	
	
	
}
