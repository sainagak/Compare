package baseCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.DifferenceEvaluators;
import org.xmlunit.diff.ElementSelectors;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class CompareXMLFiles extends ExtentListeners {
	public static int NoOfDifferences = 0;

	private static FileInputStream myControlXML;
	private static FileInputStream myTestXML;

	public void comparefiles(String Srcpath, String Trgtpath) throws Exception {

		try {
			myControlXML = new FileInputStream(Srcpath);
			test.log(Status.INFO, "The Source File is read.File name: " + Srcpath);

		} catch (FileNotFoundException e) {
			test.log(Status.SKIP, "File Not Found: " + Srcpath);
			e.printStackTrace();
			throw new SkipException("Source File Not Found");

		} catch (NullPointerException n1) {
			test.log(Status.SKIP, "The Source filepath given is NULL: " + Srcpath);
			n1.printStackTrace();
			// test.log(Status.INFO,"Error: "+n1.getMessage());
			throw new SkipException("Null Pointer Exception");

		}

		try {
			myTestXML = new FileInputStream(Trgtpath);
			test.log(Status.INFO, "The Target File is read.File name:" + Trgtpath);

		} catch (FileNotFoundException e) {
			test.log(Status.SKIP, "Target File Not Found: " + Trgtpath);
			e.printStackTrace();
			throw new SkipException("File Not Found");

		} catch (NullPointerException n2) {
			test.log(Status.SKIP, "The Target filepath given is NULL: " + Trgtpath);
			n2.printStackTrace();
			throw new SkipException("Null Pionter Exception");

		}
		try {
			// .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))

			Diff myDiffSimilar = DiffBuilder.compare(myControlXML).withTest(myTestXML).ignoreComments()

					.checkForSimilar().build();

			List differences = (List) myDiffSimilar.getDifferences();

			NoOfDifferences = differences.size();

			for (Object eachdifference : differences) {
				test.log(Status.WARNING, eachdifference.toString());
				System.out.println(eachdifference);

				String[] DifferenceFound = eachdifference.toString().split("-");
				if (DifferenceFound[0].contains("Expected child nodelist length")) {
					test.log(Status.INFO, "No of diff: " + NoOfDifferences);
					Assert.fail(eachdifference.toString());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.FAIL, "Unable To Compare,Followiing Error Encountered: " + e.getMessage());
			Assert.fail();
		} finally {
			myControlXML.close();
			myTestXML.close();

		}
	}

}
