package org.quinnox.verifyLogin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class VerifyLoginTest {

	@Test
	public void verifyLogin() throws InterruptedException, MalformedURLException {

		File src = new File("/home/ebizjboss/phantomjs-2.1.1/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
		System.setProperty("phantomjs.binary.path", src.getAbsolutePath());
		WebDriver driver = new PhantomJSDriver();

		driver.get("http://demandwaredashboard-dev.mot.com/FileDashBoard/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.className("abcRioButtonContentWrapper")).click();

		Set<String> windowID = driver.getWindowHandles();
		Iterator<String> itr = windowID.iterator();

		String parentWindowId = itr.next();
		String childWindowId = itr.next();

		System.out.println(parentWindowId);
		System.out.println(childWindowId);

		driver.switchTo().window(childWindowId);

		driver.findElement(By.id("Email")).sendKeys("manju.naidu13@gmail.com");
		driver.findElement(By.id("next")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("Passwd")).sendKeys("9886521575");
		Thread.sleep(7000);
		driver.findElement(By.id("signIn")).click();

		System.out.println("PASS");

	}

}
