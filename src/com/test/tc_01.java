package com.test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tc_01 {

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Eclipse Photon 4.8\\chromedriver_win32 (1)\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void comparePrice() {
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Apple iPhone XR (Yellow, 64 GB)");
		driver.findElement(By.xpath("(//*[@type=\"text\"])[2]/ancestor::div[6]/button")).click();
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		
		String flipkartPrice = driver
				.findElement(
						By.xpath("//*[text()='Apple iPhone XR (Yellow, 64 GB)']/following::div[4]/div[1]/div/div"))
				.getAttribute("innerText");
		
		int fPrice = Integer.parseInt(flipkartPrice.substring(1).replace(",", ""));
		
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("Apple iPhone XR (Yellow, 64 GB)");
		driver.findElement(By.xpath("//*[@type='submit']")).click();
	
		
		String amazonPrice = driver.findElement(By.xpath(
				"//*[text()='Apple iPhone XR (64GB) - Yellow']/following::span[contains(@class,'a-offscreen')][1]"))
				.getAttribute("innerText");
		
		int aPrice = Integer.parseInt(amazonPrice.substring(1).replace(",", ""));
		
		
		if(fPrice > aPrice)
		{
			System.out.println("Buy From AMAZON");
		}
		else if (fPrice < aPrice)
		{
			System.out.println("Buy From FLIPKART");
		}
		else
		{
			System.out.println("Buy From Any Of Both - Price are same on both the websites");
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
