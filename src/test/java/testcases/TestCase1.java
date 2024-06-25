package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase1 {
	WebDriver driver;

	@Test
	public void verifyTestCasesPage() {
		// 1. Launch browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// 2. Navigate to url 'https://automationexercise.com'
		driver.get("https://automationexercise.com");
		// 3. Verify that home page is visible successfully
		String expectedUrl = "https://automationexercise.com/";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		// 4. Click on 'Test Cases' button
		driver.findElement(By.xpath("//a[text()=' Test Cases']")).click();
		// 5. Verify user is navigated to test cases page successfully
		WebElement currentTestPageElement = driver.findElement(By.xpath("//*[text()='Test Cases']"));
		Assert.assertTrue(currentTestPageElement.isDisplayed());
	}

	@Test(dependsOnMethods = "verifyTestCasesPage")
	public void closeBrowser() {
		driver.close();
	}

}
