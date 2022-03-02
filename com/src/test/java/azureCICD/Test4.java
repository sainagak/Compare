package azureCICD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

import com.aventstack.extentreports.Status;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import baseCode.CompareXMLFiles;

public class Test4 extends CompareXMLFiles {
	@Test(dataProvider = "dp")
	public void ReaddataAndCompare(String Srcpath, String Trgtpath, String k) throws Exception {
		int NoOfDifferences = 0;

		FileInputStream myControlXML;
		FileInputStream myTestXML;

		try {
			myControlXML = new FileInputStream(Srcpath + ".xml");

		} catch (FileNotFoundException e) {

			throw new SkipException("Source File Not Found");

		} catch (NullPointerException n1) {

			throw new SkipException("Null Pointer Exception");

		}

		try {
			myTestXML = new FileInputStream(Trgtpath + ".xml");

		} catch (FileNotFoundException e) {

			throw new SkipException("File Not Found");

		} catch (NullPointerException n2) {

			throw new SkipException("Null Pionter Exception");

		}
		try {
			// .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))

			Diff myDiffSimilar = DiffBuilder.compare(myControlXML).withTest(myTestXML).ignoreComments()

					.checkForSimilar().build();

			List differences = (List) myDiffSimilar.getDifferences();

			NoOfDifferences = differences.size();

			for (Object eachdifference : differences) {
				System.out.println(eachdifference);

				String[] DifferenceFound = eachdifference.toString().split("-");
				if (DifferenceFound[0].contains("Expected child nodelist length")) {
					Assert.fail(eachdifference.toString());
				}

			}
			Assert.assertFalse(NoOfDifferences > 0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		} finally {
			myControlXML.close();
			myTestXML.close();

		}
	}

	@DataProvider(name = "dp")
	public Object[][] dt() throws IOException, CsvException {
		System.out.println("before reading csv file");
		CSVReader csvReader = new CSVReader(new FileReader(System.getProperty("user.dir") + "\\TD2.csv"));
		System.out.println("after reading csv file");

		System.out.println(csvReader);
		Object[][] data = null;
		List<String[]> allRows = csvReader.readAll();
		int countNoOfYesScenarios = 0;
		// we are going to read data line by line
		for (String[] row : allRows) {
			try {
				// System.out.println(Arrays.toString(row));
				if (row[0].equals("y")) {
					countNoOfYesScenarios++;

				}
			} catch (Exception e) {
				System.out.println("Execution status (Y/N) is not defined in Execution column");

			}

		}

		// create a object array
		data = new Object[countNoOfYesScenarios][3];
		int j = 0;

		for (String[] row : allRows) {
			try {

				if (row[0].equals("y")) {

					data[j][0] = row[1];
					System.out.println(data[j][0]);

					data[j][1] = row[2];
					System.out.println(data[j][1]);

					data[j][2] = row[3];
					System.out.println(data[j][2]);

					j++;
				}
			} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

				System.out.println("The Cell value of row" + Arrays.toString(row) + "is Null");

			}
		}
		return data;

	}
}