package telecomPageObject;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.LogStatus;

import testNGListeners.Listeners;

public class AddCustomePO extends Listeners {
	
	private WebDriver driver = null;
	public static HashMap<String,String> futureData = new HashMap<>();
	
	public AddCustomePO(WebDriver driver)
	{
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@FindBy(xpath="//input[@id='done']/following-sibling::label")
	private WebElement doneRadioButton;
	
	@FindBy(id="pending")
	private WebElement pendingRadioButton;
	
	@FindBy(css="#fname")
	private WebElement firstName;
	
	@FindBy(css="input[name='lname']")
	private WebElement lastName;
	
	@FindBy(id="email")
	private WebElement email;
	
	@FindBy(xpath="//textarea[@name='addr']")
	private WebElement address;
	
	@FindBy(id="telephoneno")
	private WebElement mobileNo;
	
	@FindBy(css="input[name='submit']")
	private WebElement submit;
	
	@FindBy(xpath="//tr[.//b[contains(.,'Customer ID')]]//td[2]/h3")
	private WebElement customeID;
	
	@FindBy(xpath="//a[text()='Home']")
	private WebElement homeButton;
	
	public void clickOnDoneRadioButton(HashMap<String, String> dataSet)
	{
		try {
			doneRadioButton.click();
			logger.log(LogStatus.INFO, "Done Radio Button Clicked");
		} catch (Exception e) {
			logger.log(LogStatus.INFO, "Done Radio Button Not Available");
		}
		createCustomer(dataSet);
		logger.log(LogStatus.INFO, "Entered all Data and Click on Submit button");
	}
	
	public void clickOnPendingRadioButton(HashMap<String, String> dataSet)
	{
		pendingRadioButton.click();
		createCustomer(dataSet);
		
	}
	
	public void createCustomer(HashMap<String, String> dataSet)
	{
		try {
			firstName.sendKeys(dataSet.get("FirstName"));
		} catch (Exception e) {
			logger.log(LogStatus.INFO, "Entered all Data and Click on Submit button");
			throw new RuntimeException("Issue with First Name Field");
		}
		lastName.sendKeys(dataSet.get("LastName"));
		email.sendKeys(dataSet.get("Email"));
		address.sendKeys(dataSet.get("Address"));
		mobileNo.sendKeys(dataSet.get("MobileNo"));
		submit.click();
	}
	
	public String storeCustomerID(String testCaseName)
	{
		String customerId = customeID.getText();
		futureData.put(testCaseName, customerId);
		homeButton.click();
		return customerId;
	}
}
