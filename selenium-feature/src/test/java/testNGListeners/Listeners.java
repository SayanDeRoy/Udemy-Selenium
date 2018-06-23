package testNGListeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import library.TestBase;

public class Listeners implements ITestListener{
	
	public static ExtentReports report = new ExtentReports("extentReports/ExtentReports.html");
	public static ExtentTest logger;
	
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		
		String screenShotPath = TestBase.takeScreenshot(arg0.getName());
		logger.log(LogStatus.FAIL, arg0.getName(), logger.addScreenCapture(screenShotPath));
		report.endTest(logger);
		report.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		
		logger = report.startTest(arg0.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		
		logger.log(LogStatus.PASS, arg0.getName());
		report.endTest(logger);
		report.flush();	
		
	}

}
