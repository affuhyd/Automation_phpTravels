package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import Utilities.EventListener;
import Utilities.TestUtilities;

public class ParentBase {
	public static Properties prop;
	public static WebDriver driver;
	public static String browserName;
	public static String DriverPath;
	public static EventFiringWebDriver event_driver;
	public static EventListener eventListener;
	
	public ParentBase(){
	 prop = new Properties();
	 try {
		FileInputStream fis = new FileInputStream
				("C:\\Users\\afhqhyder\\workspace\\Automation_phpTravels_V1.0\\src\\main\\java\\Config\\Application.properties");
		prop.load(fis);
	} catch (FileNotFoundException e) {
			e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
 }
	
	public static void initialize(){
		browserName = prop.getProperty("browser");
		DriverPath = prop.getProperty("DriverPath");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", DriverPath);
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", DriverPath);
			driver = new FirefoxDriver();
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", DriverPath);
			driver = new InternetExplorerDriver();
		}
		event_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new EventListener();
		event_driver.register(eventListener);
	
		driver = event_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtilities.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtilities.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
}
