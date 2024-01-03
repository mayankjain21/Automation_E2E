package testcomponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listners extends BaseTest implements ITestListener{
	
	ExtentTest test;
		ExtentReports extent=ExtentReporterNG.GetReportObject();
		ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest >();
	 
	    @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("Test Method started: " + result.getName());
	       test= extent.createTest(result.getMethod().getMethodName());
	       extentTest.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("Test Method passed: " + result.getName());
	        extentTest.get().log(Status.PASS, "Test Passed");
	    }
	    
	    public void onTestFailure(ITestResult result) {
	        System.out.println("Test Method failed: " + result.getName());
	        extentTest.get().fail(result.getThrowable());

	        WebDriver driver = null;
	        try {
	            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
	                    .get(result.getInstance());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        String filepath = null;
			try {
				filepath = getScreenShot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	    }

		/*
		 * @Override public void onTestFailure(ITestResult result) {
		 * System.out.println("Test Method failed: " + result.getName());
		 * extentTest.get().fail(result.getThrowable());
		 * 
		 * try { driver = (WebDriver)
		 * result.getTestClass().getRealClass().getField("driver")
		 * .get(result.getInstance()); } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * String filepath = null; try { filepath =
		 * getScreenShot(result.getMethod().getMethodName(),driver); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * extentTest.get().addScreenCaptureFromPath(filepath,
		 * result.getMethod().getMethodName()); }
		 */

	   

		@Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test Method skipped: " + result.getName());
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Not implemented in this example
	    }

	    @Override
	    public void onTestFailedWithTimeout(ITestResult result) {
	        // Not implemented in this example
	    }
	    
	    @Override
	    public void onStart(ITestContext context) {
	        System.out.println("Test Suite started: " + context.getName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        System.out.println("Test Suite finished: " + context.getName());
	        extent.flush();
	    }


}
