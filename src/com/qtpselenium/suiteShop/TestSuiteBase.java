package com.qtpselenium.suiteShop;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.qtpselenium.base.TestBase;
import com.qtpselenium.util.TestUtil;

public class TestSuiteBase extends TestBase{

	//check suite execution runmode
	@BeforeSuite
	public void checkSuiteSkipped() throws Exception{
		initialize();
		APP_LOGS.debug("Checking runmode of Shop Suite");
		if(! TestUtil.isSuiteRunnable(suiteXls, "Shop Suite")){
			APP_LOGS.debug("Skipped Shop Suite coz runmode was set to NO");
			throw new SkipException("Runmode of Shop Suite is set to NO. Skipped all testcases in Shop Suite");
		}
			
	}
}
