package jameel.banKChalo.testUtils;

import java.util.concurrent.TimeUnit;

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

	public static WebDriver createDriverInstance(String browser,int time) {

		if (driver.get() == null) {
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir")+"/resources/executables/geckodriver.exe");
				setDriver(new FirefoxDriver());
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")+"/resources/executables/chromedriver.exe");
				setDriver(new ChromeDriver());
			}

			else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir")+"/resources/executables/IEDriver.exe");
				setDriver(new InternetExplorerDriver());
			} else {
				System.out.println("Please Select a valid browser");
			}

		}
		maximizeBrowser();
		setImplecitWait(time);
		return getDriver();
	}

	public void killDriverInstance() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.set(null);
		}
	}
	
	
	public static void maximizeBrowser()
	{
		driver.get().manage().window().maximize();
		
	}
	public static void setImplecitWait(int time)
	{
		driver.get().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

}
