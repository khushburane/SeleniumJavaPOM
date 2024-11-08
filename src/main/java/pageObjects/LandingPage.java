package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericMethods.GenericComponents;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LandingPage extends GenericComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String pwd) {
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();
		ProductCatalogue product = new ProductCatalogue(driver);
		return product;
	}

	public String getErrorMessage() {
		
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
		 
		
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
