package seleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import library.TestBase;

public class Dummy1 {
	
	WebDriver driver = null;
	TestBase base = null;
	
	@BeforeMethod
	public void setUp()
	{
		base = new TestBase();
		driver = base.openURL("ksrtc");
	}
	@Test
	public void ksrtc()
	{
		
	}
	@Test
	public void ksrtcAgain()
	{
		driver.findElement(By.id("fromPlaceName")).sendKeys("Mysuru");
	}
	@AfterMethod
	public void tearDown()
	{
		base.closeAllBrowser();
		driver = null;
		base = null;
	}
}
