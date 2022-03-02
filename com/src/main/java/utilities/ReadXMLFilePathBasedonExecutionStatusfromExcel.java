package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import extentlisteners.ExtentListeners;

public class ReadXMLFilePathBasedonExecutionStatusfromExcel extends ExtentListeners {
	public static XSSFWorkbook workbook;
	public static FileInputStream fileinput;

	
	public static Object[][] ReadPaths(String WorktypeSheetname) throws IOException {

		Object[][] data = null;
		
		
		try {

		workbook = new XSSFWorkbook(System.getProperty("user.dir")+"//src//test//resources//testdata//Testdata.xlsx");

			XSSFSheet sheetname = workbook.getSheet(WorktypeSheetname);

			if (sheetname.getSheetName().equalsIgnoreCase(WorktypeSheetname)) {

				int rowcount = sheetname.getLastRowNum();

				int executionstatusindex = 0;
				int countNoOfYesScenarios = 0;
				while (executionstatusindex < rowcount) {

					try {
						String status1 = sheetname.getRow(executionstatusindex + 1).getCell(0).getStringCellValue();
						// System.out.println(status1);
						if (status1.equals("Y")) {
							countNoOfYesScenarios++;
						}

					} catch (NullPointerException e) {

						System.out.println("Execution status (Y/N) is not defined in Execution column");

					}
					executionstatusindex++;

				}
				System.out.println("no if yes scenarios: "+countNoOfYesScenarios);
				// create a object array
				data = new Object[countNoOfYesScenarios][3];

				int i = 0, j = 0;
				String status = null;
				// runs the loop for row count we captured abosve in sheet given
				while (i < rowcount) {

					try {

						status = sheetname.getRow(i + 1).getCell(0).getStringCellValue();

						// if the execution status in sheet is yes executes if block

						if (status.equals("Y")) {

							data[j][0] = sheetname.getRow(i + 1).getCell(1).getStringCellValue();
							//System.out.println(data[j][0]);

							data[j][1] = sheetname.getRow(i + 1).getCell(2).getStringCellValue();
							//System.out.println(data[j][1]);

							data[j][2] = sheetname.getRow(i + 1).getCell(3).getStringCellValue();

							j++;
							// increment the list index to store the value fetched from excel

							// return data;

						}
					}

					catch (NullPointerException e) {

						System.out.println("The Cell value of " + sheetname.getSheetName() + "is Null");

					}

					i++;

				}
			}

			return data;
		}

		catch (FileNotFoundException e) {
			System.out.println("The required sheet Name: " + WorktypeSheetname + " Not found in workbook ");
			return null;
		}

		finally {
			workbook.close();

		}

	}

}
