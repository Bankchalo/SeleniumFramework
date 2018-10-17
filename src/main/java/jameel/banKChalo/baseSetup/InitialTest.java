package jameel.banKChalo.baseSetup;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jameel.banKChalo.testUtils.DriverManager;

public class InitialTest {
	WebDriver driver;
	@BeforeTest
	public void beforeTest() {
		driver=DriverManager.createDriverInstance("chrome", 20);
	}
	@Test
	public void test1() {
		driver.get("https://www.gmail.com");
	}

}
