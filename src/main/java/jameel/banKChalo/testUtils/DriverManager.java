package jameel.banKChalo.testUtils;

import java.util.concurrent.TimeUnit;

import org.apache.bcel.classfile.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.server.DriverFactory;

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
				System.setProperty("webdriver.chrome.driver", Constants.getChromePath());
				setDriver(new ChromeDriver());
			}

			else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", Constants.getIEPath());
				setDriver(new InternetExplorerDriver());
			}

			else {
				System.out.println("Please Select a valid browser");
			}

		}
		maximizeBrowser();
		setImplecitWait(time);
		return getDriver();
	}

	public static void killDriverInstance() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.set(null);
		}
	}

	public static void maximizeBrowser() {
		driver.get().manage().window().maximize();

	}

	public static void setImplecitWait(int time) {
		driver.get().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

}
