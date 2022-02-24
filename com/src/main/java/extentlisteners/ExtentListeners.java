package extentlisteners;

import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_Report_" + d.toString().replace(":", "_").replace(" ", "_") + ".pdf";

	private static ExtentReports extent = ExtentManager.CreateReportFile(".\\reports\\" + fileName);

	public static  ExtentTest test;

	
	public  void onTestStart(ITestResult result) {

		test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());

	}
	
	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);

	}
	
	public void onTestFailure(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED" + "</b>";

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.log(Status.FAIL, m);

	}
	
	public void onTestSkipped(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);
		

	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		//testfail
	}
	public void onStart(ITestContext context) {
//onstart empty
	}
	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}
	public void onStart(ISuite suite) {
		//Onstart empty
	}
	public void onFinish(ISuite suite) {
		//onfinish empty
	}

}
