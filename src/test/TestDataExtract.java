package test;

import com.qtpselenium.util.Xls_Reader;

public class TestDataExtract {

	public static void main(String[] args) {

		Xls_Reader x = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\A Suite.xlsx");
		getData(x, "TestCase_A2");
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
		

		return data;
	}
}
