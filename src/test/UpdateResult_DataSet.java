package test;

import com.qtpselenium.util.Xls_Reader;

public class UpdateResult_DataSet {

	public static void main(String[] args) {

		Xls_Reader x = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\A Suite.xlsx");
		reportDataSetResult(x, "TestCase_A1", 2,"Fail");
	}

	//update a result for a particular data set
	public static void reportDataSetResult(Xls_Reader xls, String testCaseName, int rowNum, String result){
		xls.setCellData(testCaseName, "Result", rowNum, result);
	}
}
