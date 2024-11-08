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

import io.github.bonigarcia.wdm.WebDriverManager;
public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("AnshuAnu@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("AnshuAnu@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter((product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@routerlink='/dashboard/cart']")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[@routerlink='/dashboard/cart']")));
		driver.findElement(By.xpath(".//button[@routerlink='/dashboard/cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart h3")));
		List<WebElement> items = driver.findElements(By.cssSelector(".cart h3"));
		Boolean match= items.stream().anyMatch(item-> item.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".subtotal button")));
		driver.findElement(By.cssSelector(".subtotal button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".item__details div.item__title")));
		driver.findElement(By.xpath("(.//div[@class='form__cc']//input[@class='input txt'])[1]")).sendKeys("8878787878");
		driver.findElement(By.xpath("(.//div[@class='form__cc']//input[@class='input txt'])[2]")).sendKeys("anshu");
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath(".//input[@placeholder='Select Country']")), "India").build().perform();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ta-results")));
		
		driver.findElement(By.xpath("(.//span[@class='ng-star-inserted'])[2]")).click();
		driver.findElement(By.xpath(".//a[text()='Place Order ']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
		System.out.println("hello");
		driver.quit();
		//.card-body button:last-of-type
	}

}
