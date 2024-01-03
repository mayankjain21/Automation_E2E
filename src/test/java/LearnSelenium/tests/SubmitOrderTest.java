package LearnSelenium.tests;


import java.io.IOException;

import java.util.HashMap;
import java.util.List;

//import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;

import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;
import testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String ProductName="ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		ProductCatalogue productcatalogue=landingpage.Login(input.get("email"),input.get("password"));	
		//List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductTocart(input.get("product"));
		CartPage cartpage=productcatalogue.goToCartPage();
		Boolean match=cartpage.VerifyProductDisplay(input.get("product"));		
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.gotoCheckOut();
		checkoutpage.SelectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.SubmitOrder();	
		Thread.sleep(2000);
		String ConfirmationMessage=confirmationpage.GetConfirmationMessage();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
		Thread.sleep(2000);
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() throws InterruptedException
	{
		ProductCatalogue productcatalogue=landingpage.Login("anshika@gmail.com", "Iamking@000");
		Thread.sleep(2000);
		OrderPage orderpage=productcatalogue.goToOrderPage();
		Thread.sleep(2000);
		Assert.assertTrue(orderpage.VerifyOrderDisplay(ProductName));
		Thread.sleep(2000);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data=	getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//data//PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	/*
	 * HashMap<String,String> map=new HashMap<String, String>();
	 * @DataProvider public Object[][] getData() { HashMap<String,String> map=new
	 * HashMap<String, String>(); map.put("email", "anshika@gmail.com");
	 * map.put("password", "Iamking@000"); map.put("Product", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1=new HashMap<String, String>(); map1.put("email",
	 * "shetty@gmail.com"); map1.put("password", "Iamking@000"); map1.put("Product",
	 * "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] {{map},{map1}}; }
	 */
	
	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"shetty@gmail.com",
	 * "Iamking@000","ADIDAS ORIGINAL"}}; }
	 */
}
