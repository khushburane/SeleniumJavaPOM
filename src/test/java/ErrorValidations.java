import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{

	@Test(dataProvider="getData")
	public void SubmitOrder(HashMap<String,String> input) throws IOException
	{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriver driver;
		
		ProductCatalogue product=landingpage.loginApplication(input.get("email"), input.get("password"));
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		System.out.println("hello");
		//driver.quit();
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("email", "AnshuAu@gmail.com");
		map.put("password", "AnshuAu@123");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "AnshuAnu@gmail.com");
		map1.put("password", "AnhuAu@123");
		
		return new Object[][] {{map},{map1}};
	}

}
