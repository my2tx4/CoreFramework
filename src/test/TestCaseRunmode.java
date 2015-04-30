package test;

import com.qtpselenium.util.Xls_Reader;

public class TestCaseRunmode {

	public static void main(String[] args) {

		Xls_Reader x = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\A Suite.xlsx");
		System.out.println(isTestCaseRunnable(x,"TestCase_A1"));
		System.out.println(isTestCaseRunnable(x,"TestCase_A2"));
		System.out.println(isTestCaseRunnable(x,"TestCase_A3"));
		System.out.println("***************************");
		Xls_Reader x1 = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\B Suite.xlsx");
		System.out.println(isTestCaseRunnable(x1,"TestCase_B1"));
	}
	
	//return true if runmode of testcase equal to "Yes"
	public static boolean isTestCaseRunnable(Xls_Reader xls, String testcase){
		for(int i=2;i<=xls.getRowCount("TestCases");i++){
			if(xls.getCellData("TestCases", "TestCaseID", i).equalsIgnoreCase(testcase) && xls.getCellData("TestCases", "Runmode", i).equals("Y")){
				return true;
			}
		}
		xls = null;
		return false;
	}

		
}
