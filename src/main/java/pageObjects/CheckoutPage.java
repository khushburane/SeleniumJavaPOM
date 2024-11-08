package pageObjects;

import org.testng.Assert;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import genericMethods.GenericComponents;

public class CheckoutPage extends GenericComponents {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(.//div[@class='form__cc']//input[@class='input txt'])[1]")
	WebElement firstTextbox;
	@FindBy(xpath = "(.//div[@class='form__cc']//input[@class='input txt'])[2]")
	WebElement secondTextbox;

	@FindBy(xpath = ".//input[@placeholder='Select Country']")
	WebElement countryDropdown;
	@FindBy(xpath = "(.//span[@class='ng-star-inserted'])[2]")
	WebElement countryOption;
	@FindBy(xpath = ".//a[text()='Place Order ']")
	WebElement submitOrder;

	By itemdetailsBy = By.cssSelector(".item__details div.item__title");

	public void enterDetails(String first, String second) {
		waitForElementToAppear(itemdetailsBy);
		firstTextbox.sendKeys(first);
		secondTextbox.sendKeys(second);
	}

	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(countryDropdown, countryName).build().perform();
		countryOption.click();
	}

	public ConfirmationPage submitOrder() {
		submitOrder.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}
}
