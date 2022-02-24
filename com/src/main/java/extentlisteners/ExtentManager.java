package extentlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewConfigurer;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentManager {

	public static ExtentReports extent;
	public static String fileName;

	/*
	 * private ExtentManager() {
	 * 
	 * }
	 */
	protected static ExtentReports CreateReportFile(String fileName) {
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
	       
	       
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
	        
	        extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("SDET", "SAINAGA");
			extent.setSystemInfo("Application", "DPNG");
			extent.setSystemInfo("Module", "XML-Compare");
	        
	        
	        return extent;
	    }

}
