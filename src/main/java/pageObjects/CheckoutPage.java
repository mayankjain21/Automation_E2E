package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractcomponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement Submit	;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="(.//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;
	
	
	public void SelectCountry(String countryname)
	{
		Actions a=new Actions(driver);
		a.sendKeys(Country,countryname).build().perform();
		WaitForElementToAppear(By.cssSelector(".ta-results"));
		SelectCountry.click();
		//Submit.click();
		//driver.findElement(By.cssSelector(".action__submit")).click();
	}
	
	public ConfirmationPage SubmitOrder() throws InterruptedException
	{
		Thread.sleep(1000);
		WaitForWebElementToAppear(Submit);
		Submit.click();
		return new ConfirmationPage(driver);
	}

}
