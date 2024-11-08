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

public class ConfirmationPage extends GenericComponents {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By toastMessage = By.id("toast-container");
	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;

	public String verifyConfirmMessage() {
		waitForElementToAppear(toastMessage);
		String message = confirmMessage.getText();
		return message;

	}
}
