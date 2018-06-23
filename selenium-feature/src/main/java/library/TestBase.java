package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.io.FileUtils;

public class TestBase {
	
	private String propertValue;
	private FileInputStream fis = null;
	private FileOutputStream fos = null;
	private Properties pro = new Properties();
	private DesiredCapabilities capability;
	private static WebDriver driver;
	XSSFWorkbook wb = null;
	XSSFSheet sheet = null;
	
	private String readProperty(String key)
	{
		try {
			fis = new FileInputStream("environment.properties");
			pro.load(fis);
			propertValue = pro.getProperty(key);
			
		} catch (FileNotFoundException e) {
			
			System.exit(0);
			
		} catch (IOException e) {
			
			System.exit(0);
		}
		return propertValue;
	}
	private void openBrowser()
	{
		String actualBrowser = readProperty("browser");
		if(actualBrowser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			capability = DesiredCapabilities.internetExplorer();
			//capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.merge(capability);
			driver = new InternetExplorerDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		}
		else if(actualBrowser.equalsIgnoreCase("ff"))
		{
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			capability = DesiredCapabilities.firefox();
			capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capability);
			driver = new FirefoxDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		}
		else if(actualBrowser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			capability = DesiredCapabilities.chrome();
			capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ChromeOptions options = new ChromeOptions();
			options.merge(capability);
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		}
		else
		{
			throw new IllegalArgumentException("Not a Browser Type: "+actualBrowser);
		}
	}
	public WebDriver openURL(String url)
	{
		openBrowser();
		driver.get(readProperty(url));
		return driver;
	}
	public void closeCurrentBrowser()
	{
		driver.close();
		driver = null;
	}
	public void closeAllBrowser()
	{
		driver.quit();
		driver = null;
	}
	public HashMap<String,String> readExcel(String sheetName, String testCaseName)
	{
		HashMap<String,String> dataSet = new HashMap<>();
		int count = 0;
		try {
			fis = new FileInputStream("TestData/TestData.xlsx");
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
			
			for(int i = 1; i<=sheet.getLastRowNum(); i++)
			{
				String getTestCaseName = sheet.getRow(i).getCell(0).getStringCellValue();
				if(testCaseName.equalsIgnoreCase(getTestCaseName))
				{
					Iterator<Cell> itrCell = sheet.getRow(i).cellIterator();
					while(itrCell.hasNext())
					{
						dataSet.put(sheet.getRow(0).getCell(count).getStringCellValue(), itrCell.next().getStringCellValue());
						count++;
					}
					break;
				}
			}
			if(dataSet.size()>0)
			{
				fis.close();
				wb.close();
			}
			else
			{
				throw new IllegalArgumentException("Not a Valid Test Case: "+testCaseName);
			}
			
		} catch (FileNotFoundException e) {
			
			throw new IllegalArgumentException("Invalid Excell Path");
	
		} catch (IOException e) {
			
			throw new IllegalArgumentException("Load Excell Problem");
		}
		return dataSet;
	}
	public void writeExcel(String sheetName, String testCaseName, String value)
	{
		try
		{
			fis = new FileInputStream("TestData/TestData.xlsx");
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
			for(int i = 1; i<=sheet.getLastRowNum(); i++)
			{
				String getTestCaseName = sheet.getRow(i).getCell(0).getStringCellValue();
				if(testCaseName.equalsIgnoreCase(getTestCaseName))
				{
					sheet.getRow(i).createCell(6).setCellValue(value);
					fis.close();
				}
			}
			fos = new FileOutputStream("TestData/TestData.xlsx");
			wb.write(fos);
			wb.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			
			throw new IllegalArgumentException("Invalid Excell Path");
	
		} catch (IOException e) {
			
			throw new IllegalArgumentException("Load Excell Problem");
		}
		
	}
	public static String takeScreenshot(String name)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest = "extentReports/"+name+".png";
		try {
			FileUtils.copyFile(src, new File(dest));
		} catch (IOException e) {
			
			throw new IllegalArgumentException("Capture Screenshot Failed");
		}
		return "../"+dest;
	}
}
