package genericMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;

public class GenericComponents {
	WebDriver driver;

	public GenericComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = ".//button[@routerlink='/dashboard/cart']")
	WebElement cartlink;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderlink;

	By cartitem = By.cssSelector(".cart h3");

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitforElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage goToCartPage() {
		cartlink.click();
		waitForElementToAppear(cartitem);
		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}

	public OrderPage goToOrdersPage() {
		orderlink.click();
		waitForElementToAppear(cartitem);
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
}
