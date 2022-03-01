package azureCICD;

import java.io.FileReader;
import java.util.List;

import org.testng.annotations.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

import com.opencsv.CSVReader;

import baseCode.CompareXMLFiles;

public class Test4 extends CompareXMLFiles {
	@Test
	public void ReaddataAndCompare() throws Exception {
		System.out.println("before reading csv file");
		CSVReader csvReader = new CSVReader(new FileReader(System.getProperty("user.dir") + "\\TD2.csv"));
		System.out.println(csvReader);
		List<String[]> allRows = csvReader.readAll();
		for (String[] row : allRows) {

			if (row[0].equals("y")) {
				System.out.println(row[1]);
				System.out.println(row[2]);
				String source = System.getProperty("user.dir") + "\\row[1]";
				String target = System.getProperty("user.dir") + "\\row[2]";
				
			}
		}
	}
}