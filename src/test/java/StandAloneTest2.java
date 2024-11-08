import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;


public class StandAloneTest2 extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData")
	public void SubmitOrder(String email,String passwrd) throws IOException {
		// TODO Auto-generated method stub

		WebDriver driver;

		ProductCatalogue product = landingpage.loginApplication(email, passwrd);
		List<WebElement> products = product.getProductList();
		product.AddProductToCart(productName);
		CartPage cartPage = product.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.enterDetails("8878787878", "anshu");

		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationpage = checkoutPage.submitOrder();
		String msg = confirmationpage.verifyConfirmMessage();
		Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
		System.out.println("hello");
		System.out.println("Updated after git push");
		
	}

	@Test(enabled=false)
	public void ErrorSubmitOrder() throws IOException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriver driver;

		ProductCatalogue product = landingpage.loginApplication("AnshuAnu@gmail.com", "AnshuAu@123");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue product = landingpage.loginApplication("AnshuAnu@gmail.com", "AnshuAu@123");
		OrderPage orderpage = product.goToOrdersPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
	}
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {{"AnshuAnu@gmail.com","AnshuAu@123"},{"AnshuAnu@gmail.com","AnshuAu@123"}};
	}
	
	
}
