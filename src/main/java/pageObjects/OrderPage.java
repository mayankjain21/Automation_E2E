	package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".totalRow button")
	WebElement CheckoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productname;
	
	
	public Boolean VerifyOrderDisplay(String Productname)
	{
		Boolean match=productname.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(Productname));
		return match;
	}
	
	public CheckoutPage gotoCheckOut()
	{
		CheckoutEle.click();
		return new CheckoutPage(driver);
		
	}
}
