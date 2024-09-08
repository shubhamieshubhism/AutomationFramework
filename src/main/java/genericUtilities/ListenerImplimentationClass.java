package genericUtilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implimentation to ITestLitener Interface of TestNG
 */

public class ListenerImplimentationClass implements ITestListener{
	
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String testScriptName=result.getMethod().getMethodName();
		System.out.println(testScriptName+"===test Script execution started===");
		//create a test script-recognise each @test
		test=report.createTest(testScriptName);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String testScriptName=result.getMethod().getMethodName();
		System.out.println(testScriptName+"===passed===");
		//log the success
		test.log(Status.PASS,testScriptName+"===passed===");
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//screenshot
		
		// TODO Auto-generated method stub
		String testScriptName=result.getMethod().getMethodName();
		System.out.println(testScriptName+"===failed===");
		
		//exception for failure
		System.out.println(result.getThrowable());
		//will give the reason for failure
		//give you a complete exception for failure 
		//log for failure
		test.log(Status.FAIL,testScriptName+"===failed===");
		String ScreenShotName=testScriptName+new JavaUtility().getSystemDate();
		WebDriverUtility w=new WebDriverUtility();
		try {
			w.takeScreenShot(BaseClass.sdriver, ScreenShotName);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String testScriptName=result.getMethod().getMethodName();
		System.out.println(testScriptName+"===skipped===");
		//exception for failure
		System.out.println(result.getThrowable());
		//log for skip
		test.log(Status.SKIP, testScriptName+"===skipped===");
		test.log(Status.INFO,result.getThrowable());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		System.out.println("===suite execution started===");
		//basic report configuration //report-17-10-2023-20-04-20.html
		ExtentSparkReporter html = new ExtentSparkReporter(".\\ExtentReport\\report-"+new JavaUtility().getSystemDate()+".html");
		html.config().setTheme(Theme.DARK);
		html.config().setDocumentTitle("Execution Report");
		html.config().setReportName("vtiger xecution report");
		
		report=new ExtentReports();
		report.attachReporter(html);
		report.setSystemInfo("Base Browser", "chrome");
		report.setSystemInfo("Base platform", "windows");
		report.setSystemInfo("Base Enviornment", "Testing");
		report.setSystemInfo("Reporter", "Shubham");
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("===suite execution finished===");
		//report generation
		report.flush();
	}
	

}
