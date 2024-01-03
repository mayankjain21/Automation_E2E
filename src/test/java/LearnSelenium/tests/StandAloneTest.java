package LearnSelenium.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class StandAloneTest  {
	
	public static void main(String [] args) throws InterruptedException {
		String ProductName="ZARA COAT 3";
		WebDriverManager .chromedriver().setup();
		WebDriver driver=new ChromeDriver();		
		LandingPage landingpage=new LandingPage(driver);
		landingpage.Goto();
		landingpage.Login("anshika@gmail.com", "Iamking@000");
		driver.manage().window().maximize();		
		Thread.sleep(2000);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		//driver.findElement(By.xpath("(.//label[text()='household']//parent::div//input)[2]")).click();
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));	
		
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match=	cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
	Assert.assertFalse(match);
	Thread.sleep(1000);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Actions a=new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.xpath("(.//button[contains(@class,'ta-item')])[2]")).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
	String ConfirmationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	driver.close();
	
	
	
	}
}
