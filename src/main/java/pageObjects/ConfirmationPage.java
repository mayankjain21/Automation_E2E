package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

		WebDriver driver;
		public ConfirmationPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
}



		@FindBy(css=".hero-primary")
		WebElement ConfirmationMessage;
		
		public String GetConfirmationMessage()
		{
			return ConfirmationMessage.getText();
		}

}
