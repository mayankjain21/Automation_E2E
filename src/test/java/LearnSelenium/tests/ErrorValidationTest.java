package LearnSelenium.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import testcomponents.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	 @Test(groups = {"Errorhandling"})
	public void LoginErrorValidation() throws InterruptedException
	{
	
		landingpage.Login("anshika@gmail.com", "Iamkin@000");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());	
		
	}
	 @Test(groups = {"Errorhandling"})
	public void ProductErrorValidation() throws InterruptedException
	{
		String ProductName="ZARA COAT 3";
		
		ProductCatalogue productcatalogue=landingpage.Login("anshika@gmail.com", "Iamking@000");
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductTocart(ProductName);
		CartPage cartpage=productcatalogue.goToCartPage();
	
		Boolean match=cartpage.VerifyProductDisplay("Zara COAT 33");		
		Assert.assertFalse(match);
		
	}

}
