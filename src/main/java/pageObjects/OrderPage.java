package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericMethods.GenericComponents;

public class OrderPage extends GenericComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;

	public Boolean VerifyOrderDisplay(String prodName) {

		// List<WebElement> items = driver.findElements(By.cssSelector(".cart h3"));
		Boolean match = productNames.stream().anyMatch(productName -> productName.getText().equalsIgnoreCase(prodName));
		return match;
	}

}
