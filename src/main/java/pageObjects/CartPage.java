package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".totalRow button")
	WebElement CheckoutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> productTitle;
	
	
	public Boolean VerifyProductDisplay(String Productname)
	{
		Boolean match=productTitle.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(Productname));
		return match;
	}
	
	public CheckoutPage gotoCheckOut()
	{
		CheckoutEle.click();
		return new CheckoutPage(driver);
		
	}
	
}
