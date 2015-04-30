package com.qtpselenium.suiteProductDisplay;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.suiteShop.TestSuiteBase;
import com.qtpselenium.util.TestUtil;

public class TestCase_C2 extends TestSuiteBase{

	@BeforeTest
	public void checkTestSkipped(){
		if(! TestUtil.isTestCaseRunnable(suite_productDisplay_Xls, this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO");
			throw new SkipException("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO");
		}
	}
	
	@Test(dataProvider="getTestData")
	public void testCase_C2(){
		System.out.println("testCaseC2");
	}
	
		
}
