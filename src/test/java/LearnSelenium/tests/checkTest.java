package LearnSelenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import testcomponents.BaseTest;

public class checkTest {
	
	public class ErrorValidationTest extends BaseTest {
		
		 @Test(groups = {"Errorhandling"})
		public void LoginErrorValidation() throws InterruptedException
		{
		
			landingpage.Login("anshika@gmail.com", "Iamkin@000");
			Assert.assertEquals("ncorrect email or password.", landingpage.getErrorMessage());	
			
		}

}}
