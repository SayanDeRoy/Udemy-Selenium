package createTelecomCustomer;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import library.TestBase;
import telecomPageObject.AddCustomePO;
import telecomPageObject.TelecomHomePO;

public class CreateCustomer {
	
	WebDriver driver;
	TestBase base;
	TelecomHomePO home;
	HashMap<String,String> dataSet;
	String test;
	
	@BeforeTest
	public void testSetUp()
	{
		base = new TestBase();
		driver = base.openURL("telecom");
		home = new TelecomHomePO(driver);
	}
	@Test
	public void createCustomer1()
	{
		dataSet = base.readExcel("Data", "createCustomer1");
		home.clickOnAddCustomer();
		AddCustomePO addCustomer = PageFactory.initElements(driver, AddCustomePO.class);
		addCustomer.clickOnDoneRadioButton(dataSet);
		String customerID = addCustomer.storeCustomerID("createCustomer1");
		if(customerID!=null)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	@Test
	public void createCustomer2()
	{
		dataSet = base.readExcel("Data", "createCustomer2");
		home.clickOnAddCustomer();
		AddCustomePO addCustomer = PageFactory.initElements(driver, AddCustomePO.class);
		addCustomer.clickOnDoneRadioButton(dataSet);
		String customerID = addCustomer.storeCustomerID("createCustomer2");
		if(customerID!=null)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	@AfterTest
	public void tearDown()
	{
		base.closeAllBrowser();
		driver = null;
	}
}
