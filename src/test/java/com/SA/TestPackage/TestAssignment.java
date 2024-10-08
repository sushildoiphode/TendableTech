package com.SA.TestPackage;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAssignment {
	WebDriver driver;
	
	@BeforeClass
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.tendable.com/");
	}
	
	@Test
	public void assignment() throws InterruptedException {
		String[] menuItems = {"Our Story","Our Solution","Why Tendable?","About Us"};
		
		for(String item : menuItems) {
			WebElement menu = driver.findElement(By.linkText(item));
			Assert.assertTrue(menu.isDisplayed());
			
			menu.click();
			WebElement requestDemoText =  driver.findElement(By.xpath("//a[text()='Request A Demo']"));
			Assert.assertTrue(requestDemoText.isDisplayed());
		}
		
		driver.findElement(By.linkText("Contact Us")).click();
		
		driver.findElement(By.xpath("//div[contains(text(),'Marketing')]//parent::div//*[text()='Contact']")).click();
		
		driver.findElement(By.xpath("(//input[@name='fullName'])[1]")).sendKeys("sushil");
		
		driver.findElement(By.xpath("(//input[@name='organisationName'])[1]")).sendKeys("ABCD");
		
		driver.findElement(By.xpath("(//input[@name='cellPhone'])[1]")).sendKeys("123456789");
		
		driver.findElement(By.xpath("(//input[@name='email'])[1]")).sendKeys("abc@gmail.com");
		
		WebElement roll = driver.findElement(By.xpath("(//select[@name='jobRole'])[1]"));
		Select s = new Select(roll);
		s.selectByVisibleText("Executive Board Member");
		
		WebElement radioButton = driver.findElement(By.xpath("(//input[@name='consentAgreed'])[1]"));
		scrollIntoView(radioButton);
		radioButton.click();
		
	//	WebElement ourLocation = driver.findElement(By.xpath("//*[text()='Our Locations']"));
	//	scrollIntoView(ourLocation);
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//button[@name='form_page_submit'])[1]")));
		
	//	WebElement submit = driver.findElement(By.xpath("(//button[@name='form_page_submit'])[1]"));
	//	scrollIntoView(submit);
	//	submit.click();
		
		String actualErrorMessage = driver.findElement(By.xpath("//*[text()='Sorry, there was an error submitting the form. Please try again.']")).getText();
		String expectedErrorMessage = "Sorry, there was an error submitting the form. Please try again.";
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		
		
		String actualErrorMessage1 = driver.findElement(By.xpath("//*[text()='This field is required']")).getText();
		String expectedErrorMessage1 = "This field is required']";
		
		Assert.assertEquals(actualErrorMessage1, expectedErrorMessage1);	
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver ; 
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
