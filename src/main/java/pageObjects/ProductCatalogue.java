package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By ToastMessage =By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		WaitForElementToAppear(productBy);
		return products;
	}
	
	//product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();		
	public WebElement GetProductByName(String ProductName)
	{
		WebElement prod=getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);	
		return prod;
	}

	public void addProductTocart(String productname) throws InterruptedException
	{
	  WebElement prod=GetProductByName(productname);
	  prod.findElement(addToCart).click();
	  WaitForElementToAppear(ToastMessage);
	  WaitForElementToDisappear(spinner);
	}

	
}
