package telecomPageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import testNGListeners.Listeners;

public class TelecomHomePO extends Listeners {
	
	private WebDriver driver = null;
	
	public TelecomHomePO(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@FindBy(xpath="//div[@class='flex-item left']//child::a[@href='addcustomer.php']")
	private WebElement addCustomer;
	
	@FindBy(xpath="//div[@class='flex-item left']//child::a[contains(@href,'assigntariffplantocustomer')]")
	private WebElement addTariffPlanToCustomer;
	
	@FindBy(xpath="//div[@class='flex-item right']//child::a[@href='addtariffplans.php']")
	private WebElement addTariffPlan;
	
	@FindBy(xpath="//div[@class='flex-item right']//child::a[@href='billing.php']")
	private WebElement payBilling;
	
	public void clickOnAddCustomer()
	{
		try {
			addCustomer.click();
			logger.log(LogStatus.INFO, "Click on Add Customer");
		} catch (Exception e) {
			logger.log(LogStatus.INFO, "Add Customer Link Not Available");
		}
	}
	public void clickOnAddTariffPlanToCustomer()
	{
		addTariffPlanToCustomer.click();
	}
	public void clickOnAddTariffPlan()
	{
		addTariffPlan.click();
	}
	public void clickOnPayBilling()
	{
		payBilling.click();
	}
}
