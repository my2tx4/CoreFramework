package com.qtpselenium.suiteShop;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.util.ErrorUtil;
import com.qtpselenium.util.TestUtil;

public class RandomSelection extends TestSuiteBase{
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
	
	
	@Test(dataProvider="getTestData")
	public void randomSelection(String UserName,String Password,String Email){
		count++;
		if(! runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test is set to no  "+ count); 
		}
		//in report
		APP_LOGS.debug("Executing testCaseA1");
		APP_LOGS.debug(UserName+" - "+Password+" - "+" - "+Email);
		
		//selenium code
		String expected_title="ABC page";
		String actual_title="ABC page1";
		
		try{
		Assert.assertEquals(expected_title, actual_title);
		}catch(Throwable t){
			//code to report the error in testng
			ErrorUtil.addVerificationFailure(t);
			//report the error in xls file
			fail=true;
			return; //- stop testcase
		}
		System.out.println("testcase continued");
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

