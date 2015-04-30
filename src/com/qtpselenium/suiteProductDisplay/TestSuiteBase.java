package com.qtpselenium.suiteProductDisplay;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.qtpselenium.base.TestBase;
import com.qtpselenium.util.TestUtil;

public class TestSuiteBase extends TestBase{

	//check suite execution runmode
	@BeforeSuite
	public void checkSuiteSkipped() throws Exception{
		initialize();
		APP_LOGS.debug("Checking runmode of Product Display Suite");
		if(! TestUtil.isSuiteRunnable(suiteXls, "Product Display Suite")){
			APP_LOGS.debug("Skipped Suite C coz runmode set to NO");
			throw new SkipException("Runmode of Suite C is set to NO. Skipped all testcases in Product Display Suite");
		}
				
	}
}
