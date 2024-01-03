package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.reporters.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver InitializeBrowser() throws IOException
	{
		
		 Properties prop = new Properties();
	        FileInputStream fis = null;

	   
	            // Use File.separator for better cross-platform compatibility
	            String filePath = System.getProperty("user.dir") + File.separator +
	                              "src" + File.separator +
	                              "main" + File.separator +
	                              "java" + File.separator +
	                              "resources" + File.separator +
	                              "GlobalData.properties";

	            fis = new FileInputStream(filePath);
	            prop.load(fis);
	            
	            String BrowserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");	      
	            //String BrowserName = prop.getProperty("browser");	      
		
		if(BrowserName.contains("chrome"))
		{
		WebDriverManager .chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		//option.addArguments("--disable notifications");
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(ChromeOptions.CAPABILITY, option);
		option.merge(dc);
		if(BrowserName.contains("headless"))
		{
		option.addArguments("headless");
		}
		driver=new ChromeDriver(option);	
		driver.manage().window().setSize(new Dimension(1440, 900));
	
		}
		else if(BrowserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager .firefoxdriver().setup();
		    driver=new FirefoxDriver();		
			
		}
		
		else if(BrowserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();		
		
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		 String jsoncontent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		 ObjectMapper mapper=new ObjectMapper();
		 List<HashMap<String,String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){
         
	});
		 return data;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=InitializeBrowser();
		landingpage=new LandingPage(driver);
		landingpage.Goto();
		return landingpage;
	}
	
	public String getScreenShot(String testcaseName, WebDriver driver ) throws IOException
	{
		/*
		 * TakesScreenshot ts=(TakesScreenshot) driver; File
		 * source=ts.getScreenshotAs(OutputType.FILE); File file=new
		 * File(System.getProperty(("user.dir")+"//reports//"+ testcaseName+".png"));
		 * FileUtils.copyFile(source, file); return
		 * System.getProperty("user.dir")+"//reports//"+ testcaseName+".png";
		 */	
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);	        
	        // Remove the parentheses around "user.dir"
	        File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");	        
	        FileUtils.copyFile(source, file);
	        return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
		
	}
	
	
	
	@AfterMethod(alwaysRun=true)	
	public void TearDown()
	{
		driver.close();
	}
}
