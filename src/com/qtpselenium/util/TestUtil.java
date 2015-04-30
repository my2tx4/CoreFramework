package com.qtpselenium.util;

public class TestUtil {

	public static boolean isSuiteRunnable(Xls_Reader xls, String suiteName){
		boolean isExecutable = false;	
		for (int i=2;i<=xls.getRowCount("Suites");i++){
			if (xls.getCellData("Suites", "TestSuiteID", i).equalsIgnoreCase(suiteName) && xls.getCellData("Suites", "Runmode", i).equalsIgnoreCase("Y")){
				isExecutable=true;
			}
		}
		xls=null; //release memory
		return isExecutable;
	}
	
	
	public static boolean isTestCaseRunnable(Xls_Reader xls, String testCaseName){
		for(int i=2;i<=xls.getRowCount("TestCases");i++){
			if(xls.getCellData("TestCases", "TestCaseID", i).equalsIgnoreCase(testCaseName) && xls.getCellData("TestCases", "Runmode", i).equals("Y")){
				return true;
			}
		}
		xls = null;
		return false;
	}
	
	//return test data in a two dimensional array
		public static Object[][] getData(Xls_Reader xls, String testCaseName){
			
			if(! xls.isSheetExist(testCaseName)){
				xls=null;
				return new Object[1][0];
			}
			int rows = xls.getRowCount(testCaseName);
			int cols = xls.getColumnCount(testCaseName);
			Object[][] data = new Object[rows-1][cols-3];
			
			for(int rNum=2;rNum<=rows;rNum++){
				for(int cNum=0;cNum<cols-3;cNum++){
					data[rNum-2][cNum] = xls.getCellData(testCaseName, cNum, rNum);
				}
			}
			xls = null;
			return data;
		}
		
		public static String[] getDataSetRunmodes(Xls_Reader xlsFile,String sheetName){
			String[] runmodes=null;
			if(!xlsFile.isSheetExist(sheetName)){
				runmodes = new String[1];
				runmodes[0]="Y";
				xlsFile=null;
				sheetName=null;
				return runmodes;
			}
			runmodes = new String[xlsFile.getRowCount(sheetName)-1];
			for(int i=2;i<=runmodes.length+1;i++){
				runmodes[i-2]=xlsFile.getCellData(sheetName, "Runmode", i);
			}
			xlsFile=null;
			sheetName=null;
			return runmodes;
			
		}
		
		//update a result for a particular data set
		public static void reportDataSetResult(Xls_Reader xls, String testCaseName, int rowNumber, String result){
			xls.setCellData(testCaseName, "Result", rowNumber, result);
		}
		
		//return row number of test
		public static int getTestCaseRowNumber(Xls_Reader xls, String testCaseName){
			return xls.getCellRowNum("TestCases", "TestCaseID", testCaseName);
		}
}
