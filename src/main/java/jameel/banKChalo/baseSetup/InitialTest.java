package jameel.banKChalo.baseSetup;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;

import jameel.banKChalo.customListeners.CustomListeners;
import jameel.banKChalo.testUtils.DriverManager;
import jameel.banKChalo.testUtils.ExtentManager;
import jameel.banKChalo.testUtils.FiloReader;
import junit.framework.Assert;

@Listeners(CustomListeners.class)
public class InitialTest {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> classLevelReport = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelReport = new ThreadLocal<ExtentTest>();

	@BeforeSuite
	public void beforeSuite() {
		// Load Config Files

		extent = ExtentManager.getExtent();
	}

	@BeforeClass
	public void beforeClass() {
		ExtentTest parent = extent.createTest(getClass().getSimpleName());
		classLevelReport.set(parent);
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		driver = DriverManager.getDriverInstance("chrome", 20);
		driver.get("http://newtours.demoaut.com/");
		ExtentTest test = classLevelReport.get().createNode(m.getName());
		testLevelReport.set(test);
	}

	
	@AfterMethod
	public void afterMethod(Method m,ITestResult result) {
		DriverManager.killDriverInstance();
		extent.flush();
	}

	@DataProvider(name = "dataProvider")
	public Object[][] data(Method m) throws FilloException{
		int j = 0;
		Recordset record;
		Object[][] data = null;

		FiloReader filo = new FiloReader();
		filo.createConnection();
		System.out.println(m.getName());
		Recordset recordSet = filo.executeQuery("Select * from TestData where TC_ID='"+m.getName()+"'");
		
		Hashtable<String, String> tab = null;

		data = new Object[recordSet.getCount()][1];

		if (recordSet != null) {
			while (recordSet.next()) {
				tab = new Hashtable<String, String>();
				for (int i = 0; i < recordSet.getFieldNames().size(); i++) {
					tab.put(recordSet.getField(i).name(), recordSet.getField(recordSet.getField(i).name()));

				}
				data[j][0] = tab;
				
				j++;
			}

		}
		
		return data;

	}

}
