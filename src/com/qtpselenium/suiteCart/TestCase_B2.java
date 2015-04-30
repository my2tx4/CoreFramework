package com.qtpselenium.suiteCart;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.suiteShop.TestSuiteBase;
import com.qtpselenium.util.TestUtil;

public class TestCase_B2 extends TestSuiteBase{

	@BeforeTest
	public void checkTestSkipped(){
		if(! TestUtil.isTestCaseRunnable(suite_cart_Xls, this.getClass().getSimpleName())){
			APP_LOGS.debug("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO");
			throw new SkipException("Skipping test case "+this.getClass().getSimpleName()+" coz runmode is set to NO");
		}
	}
	
	@Test(dataProvider="getTestData")
	public void testCase_B2(){
		System.out.println("testCaseB2");
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(suite_cart_Xls, this.getClass().getSimpleName());
	}
}
