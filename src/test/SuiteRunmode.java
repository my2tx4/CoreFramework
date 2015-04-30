package test;

import java.util.ArrayList;
import java.util.List;

import com.qtpselenium.util.Xls_Reader;

public class SuiteRunmode {

	public static void main(String[] args) {
		
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"//src//com/qtpselenium//xls//Suite.xlsx");
		System.out.println(isSuiteRunnable(xls,"A Suite"));
		System.out.println(isSuiteRunnable(xls,"B Suite"));
		System.out.println(isSuiteRunnable(xls,"C Suite"));
	}
	
	// finds if test suite is runnable
	public static boolean isSuiteRunnable(Xls_Reader xls, String suite){
		//boolean isExecutable = false;	
		for (int i=2;i<=xls.getRowCount("Suites");i++){
			if (xls.getCellData("Suites", "TestSuiteID", i).equalsIgnoreCase(suite) && xls.getCellData("Suites", "Runmode", i).equalsIgnoreCase("Y")){
				//isExecutable=true;
				return true;
			}
		}
		xls=null; //release memory
		//return isExecutable;
		return false;
	}
}
