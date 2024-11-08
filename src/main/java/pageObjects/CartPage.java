package pageObjects;

import org.testng.Assert;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import genericMethods.GenericComponents;

public class CartPage extends GenericComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cart h3")
	List<WebElement> items;
	
	@FindBy(css=".subtotal button")
	WebElement subtotal;
	By subtotalBy = By.cssSelector(".subtotal button");

	public Boolean VerifyProductDisplay(String prodName) {
		
		// List<WebElement> items = driver.findElements(By.cssSelector(".cart h3"));
		Boolean match = items.stream().anyMatch(item -> item.getText().equalsIgnoreCase(prodName));
		return match;
	}
	public CheckoutPage goToCheckoutPage() {
		waitForElementToAppear(subtotalBy);
		subtotal.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
			
	}

}
