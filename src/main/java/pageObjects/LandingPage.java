package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(	WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="userEmail")
	WebElement Useremail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement Submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorMessage;
	
	
	public String getErrorMessage()
	{
		WaitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
	}
	public ProductCatalogue Login(String email, String Password)
	{
		Useremail.sendKeys(email);
		password.sendKeys(Password);
		Submit.click();
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public void Goto()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
