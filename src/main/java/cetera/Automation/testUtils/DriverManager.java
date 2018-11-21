
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

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
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

		String OS = System.getProperty("os.name").toString().toLowerCase();
		if (OS.contains("mac"))
			OS = "MAC";
		else if (OS.contains("win"))
			OS = "WINDOWS";

		switch (OS) {
		case "WINDOWS":
			if (driver.get() == null) {
				if (browser.equalsIgnoreCase("FIREFOX")) {
					System.setProperty("webdriver.gecko.driver", Constants.getFirefoxPath());
					setDriver(new FirefoxDriver());
				}

				else if (browser.equalsIgnoreCase("CHROME")) {
					/*
					 * ChromeOptions option = new ChromeOptions();
					 * option.addArguments("--disbale-notifications");
					 * option.addArguments("disbale-infobars");
					 * option.addArguments("--start-maximized");
					 * option.setPageLoadStrategy(PageLoadStrategy.EAGER);
					 * System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,
					 * "true");
					 */
					System.setProperty("webdriver.chrome.driver", Constants.getChromePath());
					setDriver(new ChromeDriver());
				}

				else if (browser.equalsIgnoreCase("IE")) {
					System.setProperty("webdriver.ie.driver", Constants.getIEPath());
					setDriver(new InternetExplorerDriver());
				} else if (browser.equalsIgnoreCase("headless")) {
					setDriver(new HtmlUnitDriver());
				}

				else {
					System.out.println("Please Select a valid browser");
				}
			}

		case "MAC":
			if (driver.get() == null) {
				if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.gecko.driver", Constants.getChromePathMac());
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					options.addArguments("disable-infobars");
					options.addArguments("--start-maximized");
					// options.addArguments("--headless");
					options.addArguments("--proxy-server:http://100.0.0.1");
					// options.addArguments("user-data-dir:directory path of till user data
					// folder");
					// options.setPageLoadStrategy(PageLoadStrategy.EAGER);
					System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "browserLogs");
					System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

					setDriver(new ChromeDriver(options));
				} else if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", Constants.getFirefoxPathMac());

					// FireFox profile options setting:
					System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "browserLogs");
					FirefoxOptions options = new FirefoxOptions();
					options.setPageLoadStrategy(PageLoadStrategy.NONE);
					ProfilesIni prof = new ProfilesIni();
					FirefoxProfile profile = prof.getProfile("default");
					profile.setPreference("dom.webnotifictions.enabled", false);

					// Untrusted certificate acceptance:
					profile.setAcceptUntrustedCertificates(true);
					profile.setAssumeUntrustedCertificateIssuer(false);

					options.setProfile(profile);
					setDriver(new FirefoxDriver(options));
				} else if (browser.equalsIgnoreCase("safari")) {
					System.setProperty("webdriver.safari.noinstall", "true");
					setDriver(new SafariDriver());
				}

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
		if (!browser.equals("headless"))
			driver.get().manage().window().maximize();

	}

	public static void setImplicitWait(int time) {
		driver.get().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

}
