package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericMethods.GenericComponents;

public class ProductCatalogue extends GenericComponents{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement ele;
	
	@FindBy(xpath=".//button[@routerlink='/dashboard/cart']")
	WebElement cartLink;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter((product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))).findFirst().orElse(null);
		return prod;
	}
	
	public void AddProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitforElementToDisappear(ele);
	}
	
	public CartPage goToCartPage() {
		cartLink.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	
}
