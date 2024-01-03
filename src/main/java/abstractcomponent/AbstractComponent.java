package abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement OrderHeader;

	public void WaitForElementToAppear(By findby)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void WaitForWebElementToAppear(WebElement findby)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public void WaitForElementToDisappear(WebElement ele) throws InterruptedException
	{   
		Thread.sleep(2000);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;		
	}
	
	
	public OrderPage goToOrderPage()
	{
		OrderHeader.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
		
	}
}
