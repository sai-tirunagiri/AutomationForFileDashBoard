package org.quinnox.verifyLogin;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class VerifyLoginTest {
	
	@Test
	public void verifyLogin() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\saiprasadt\\Desktop\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://localhost:8081/FileDashBoard/login.html");
		Thread.sleep(4000);
		driver.findElement(By.className("abcRioButtonIcon")).click();
		
		
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		System.out.println("Parent ::"+parentWindowHandler);
		System.out.println("Child ::"+subWindowHandler);
		
		driver.switchTo().window(subWindowHandler);
		Thread.sleep(2000);
		driver.findElement(By.id("Email")).sendKeys("manju.naidu13@gmail.com");
		driver.findElement(By.id("next")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("Passwd")).sendKeys("7411677660");
		
		driver.findElement(By.id("signIn")).click();
		Thread.sleep(5000);
		
		driver.switchTo().window(parentWindowHandler);
		String actual = "Stuck files from past hour";
		String expected = driver.findElement(By.xpath("//div[contains(text(),'Stuck files')]")).getText();
		
		//System.out.println("expected ::"+expected);
		if(actual.equalsIgnoreCase(expected)){
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
	}

}
