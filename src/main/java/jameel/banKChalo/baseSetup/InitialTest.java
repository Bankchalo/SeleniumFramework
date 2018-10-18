package jameel.banKChalo.baseSetup;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import jameel.banKChalo.customListeners.CustomListeners;
import jameel.banKChalo.testUtils.DriverManager;
import jameel.banKChalo.testUtils.ExtentManager;
import junit.framework.Assert;

@Listeners(CustomListeners.class)
public class InitialTest {
	WebDriver driver;	
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> classLevelReport = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelReport = new ThreadLocal<ExtentTest>();
	
	@BeforeSuite
	public void beforeSuite() {
		//Load Config Files
		
		extent=ExtentManager.getExtent();
	}
	
	@BeforeClass
	public void beforeClass() {
		ExtentTest parent = extent.createTest(getClass().getSimpleName());
		classLevelReport.set(parent);
	}

	
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		driver=DriverManager.getDriverInstance("chrome", 20);
		ExtentTest test = classLevelReport.get().createNode(m.getName());
		testLevelReport.set(test);
	}
	
	
	@Test
	public void test1() {
		driver.get("https://www.google.com");
		testLevelReport.get().debug("Google Launched");
		
		
	}
	
	@Test
	public void test2() {
		driver.get("https://www.gmail.com");
		testLevelReport.get().debug("Gmail Launched");
		Assert.fail();
	}
	
	@Test(enabled=false)
	public void test3() {
		
	}
	
	@AfterMethod
	public void afterMethod() {
		DriverManager.killDriverInstance();
		extent.flush();
	}

}
