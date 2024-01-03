package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports GetReportObject()
	{
		String path=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter ex=new ExtentSparkReporter(path);
		ex.config().setReportName("Web Automation Results");
		ex.config().setDocumentTitle("Test Result");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(ex);
		extent.setSystemInfo("Tester", "Mayank Jain");
		extent.createTest(path);
		return extent;
		
	}

}
