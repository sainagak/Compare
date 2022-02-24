package azureCICD;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import org.testng.Assert;
import org.testng.SkipException;
import org.xmlunit.builder.DiffBuilder;

import org.testng.annotations.Test;
import org.xmlunit.diff.Diff;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class Test3compare extends ExtentListeners {
@Test
public void test3() throws FileNotFoundException {
	FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir")+"\\com\\src\\test\\resources\\src\\src1.xml");
	FileInputStream fis2 = new FileInputStream(System.getProperty("user.dir")+"\\com\\src\\test\\resources\\trgt\\trgt1.xml");
			
	// using BufferedReader for improved performance
	BufferedReader source = new BufferedReader(new InputStreamReader(fis1));
	BufferedReader target = new BufferedReader(new InputStreamReader(fis2));

	
	
	// XMLUnit.setCompareUnmatched(true);//node squence but not working so
	Diff myDiffSimilar = DiffBuilder.compare(source).withTest(target).ignoreComments()

			.checkForSimilar().build();

	
	List differences = (List) myDiffSimilar.getDifferences();

	int NoOfDifferences = differences.size();

	for (Object eachdifference : differences) {
		test.log(Status.WARNING, eachdifference.toString());
		System.out.println(eachdifference);
		
		String[] DifferenceFound = eachdifference.toString().split("-");
		if (DifferenceFound[0].contains("Expected child nodelist length")) {
			test.log(Status.INFO, "No of diff: " + NoOfDifferences);
			Assert.fail(eachdifference.toString());
		}

	}


}
}
