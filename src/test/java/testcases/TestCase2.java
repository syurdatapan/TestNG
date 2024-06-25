package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase2 {
	WebDriver driver;

	@Test
	public void searchProduct() {
		// 1. Launch browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// 2. Navigate to url 'http://automationexercise.com'
		driver.get("http://automationexercise.com");
		// 3. Verify that home page is visible successfully
		String expectedUrl = "https://automationexercise.com/";
		String actualUrl = driver.getCurrentUrl();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualUrl, expectedUrl);
		// 4. Click on 'Products' button
		driver.findElement(By.xpath("//a[text()=' Products']")).click();
		// 5. Verify user is navigated to ALL PRODUCTS page successfully
		WebElement allProductsElement = driver.findElement(By.xpath("//h2[text()='All Products']"));
		softAssert.assertTrue(allProductsElement.isDisplayed());
		// 6. Enter product name in search input and click search button
		driver.findElement(By.xpath("//input[@id='search_product']")).sendKeys("Rs. 600");
		driver.findElement(By.xpath("//button[@id='submit_search']")).click();
		// 7. Verify 'SEARCHED PRODUCTS' is visible
		WebElement searchedProductsElement = driver.findElement(By.xpath("//h2[text()='Searched Products']"));
		softAssert.assertTrue(searchedProductsElement.isDisplayed());
	}

	@Test(dependsOnMethods = "searchProduct")
	public void closeBrowser() {
		driver.close();
	}
}
