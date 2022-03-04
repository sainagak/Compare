package azureCICD;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.opencsv.exceptions.CsvException;

import baseCode.CompareXMLFiles;
import utilities.ReadXMLFilePathBasedonExecutionStatusfromExcel;
import utilities.ReadXMLFilePathFromCSV;

public class Test6CSV extends CompareXMLFiles {
	@Test(dataProvider = "dp", enabled = true)
	public void test6CSV(String i, String j, String k) throws Exception {

		test.assignAuthor("SAINAGA").assignCategory("FUNCTIONAL").assignDevice("XML");

		test.log(Status.INFO, "Test Case Name: " + k);

		comparefiles(System.getProperty("user.dir")+"//"+i+".xml", System.getProperty("user.dir")+"//"+j+".xml"); //
		//System.out.println(NoOfDifferences);
		test.log(Status.INFO, "Total No of actual differences after filtering: " + NoOfDifferences);
       test.log(Status.FAIL, "test failed");
	//  Assert.assertFalse(NoOfDifferences > 0);

	}

	@DataProvider(name = "dp")
	public Object[][] getdata() throws IOException, CsvException {

		return ReadXMLFilePathFromCSV.ReadPaths("testDataworktype2");
		
	}
}



