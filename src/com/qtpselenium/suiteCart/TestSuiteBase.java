package com.qtpselenium.suiteCart;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.qtpselenium.base.TestBase;
import com.qtpselenium.util.TestUtil;

public class TestSuiteBase extends TestBase{

	//check suite execution runmode
	@BeforeSuite
	public void checkSuiteSkipped() throws Exception{
		initialize();
		APP_LOGS.debug("Checking runmode of Cart Suite");
		if(! TestUtil.isSuiteRunnable(suiteXls, "Cart Suite")){
			APP_LOGS.debug("Skipped Suite B coz runmode set to NO");
			throw new SkipException("Runmode of Suite B is set to NO. Skipped all testcases in Cart Suite");
		}
				
	}
}
