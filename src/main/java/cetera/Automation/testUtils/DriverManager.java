
/**
 * ClassName :- DriverManager
 * Invokes the browser
 * 
 * Created By 	:- Umesh Joshi/Viral Singh
 * Created Date :- 17-Nov 2018
 * Modified By 	:- 
 * Modified Date:- 
 *
 */


package cetera.Automation.testUtils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cetera.Automation.customListeners.CustomListeners;

public class DriverManager {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();


	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver dvr) {
		driver.set(dvr);
	}

	public static WebDriver getDriverInstance(String browser, int time) {
		
		if (driver.get() == null) {
			if (browser.equalsIgnoreCase("FIREFOX")) {
				System.setProperty("webdriver.gecko.driver", Constants.getFirefoxPath());
				setDriver(new FirefoxDriver());
			}

			else if (browser.equalsIgnoreCase("CHROME")) {
				/*ChromeOptions option = new ChromeOptions();
				option.addArguments("--disbale-notifications");
				option.addArguments("disbale-infobars");
				option.addArguments("--start-maximized");
				option.setPageLoadStrategy(PageLoadStrategy.EAGER);
				System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");*/
				System.setProperty("webdriver.chrome.driver", Constants.getChromePath());
				setDriver(new ChromeDriver());
			}

			else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", Constants.getIEPath());
				setDriver(new InternetExplorerDriver());
			}
			else if(browser.equalsIgnoreCase("headless")) {
				setDriver(new HtmlUnitDriver());
			}

			else {
				System.out.println("Please Select a valid browser");
			}
			

		}
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver.get());
		CustomListeners listen = new CustomListeners();
		edriver.register(listen);
		setDriver(edriver);
		maximizeBrowser(browser);
		setImplicitWait(time);
		return getDriver();
	}

	public static void killDriverInstance() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.set(null);
		}
	}

	public static void maximizeBrowser(String browser) {
		if(!browser.equals("headless"))
			driver.get().manage().window().maximize();

	}

	public static void setImplicitWait(int time) {
		driver.get().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

}
