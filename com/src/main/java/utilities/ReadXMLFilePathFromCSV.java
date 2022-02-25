package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadXMLFilePathFromCSV {

	static CSVReader csvReader;
	static String testDataCSV;
	public static FileInputStream DataPropertiesfile;

	public static Object[][] ReadPaths(String CSVfileOftestData) throws IOException, CsvException {

		Object[][] data = null;
		final Properties prop;
		prop = new Properties();
		try {
			// reading data properties file for testData CSV file path
			DataPropertiesfile = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/resources/data.properties");
			System.out.println(System.getProperty("user.dir") + "\\com\\src\\test\\resources\\resources\\data.properties");
			prop.load(DataPropertiesfile);

			testDataCSV = prop.getProperty(CSVfileOftestData);

			csvReader = new CSVReader(
					new FileReader(System.getProperty("user.dir") + "\\com\\src\\test\\resources\\testdata\\" + testDataCSV));

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
						//System.out.println(data[j][0]);

						data[j][1] = row[2];
						//System.out.println(data[j][1]);

						data[j][2] = row[3];
						//System.out.println(data[j][2]);

						j++;
					}
				} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

					System.out.println("The Cell value of row" + Arrays.toString(row) + "is Null");

				}
			}
			return data;
		}

		catch (FileNotFoundException e) {
		System.out.println("The required CSV file: " + CSVfileOftestData
					+ " Not found in datafile or TestData file properties files");
			System.out.println(System.getProperty("user.dir") + "\\com\\src\\test\\resources\\resources\\data.properties");
			return null;
		}

		finally {
			csvReader.close();

		}

	}

}
