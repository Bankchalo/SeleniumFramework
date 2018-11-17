/**
 * ClassName :- Inital Test
 * This Class is used for the basic presteps that are required to run a Selenium Test.
 * The below items are setup in this class
 * --loads the config file
 * --setup the test Rail connection
 * --creates a test Run in test Rail
 * --initializes the Extent Reports
 * --initializes log4j
 * --invokes the browser
 * --contains dataProvider
 * 
 * Created By 	:- Umesh Joshi/Viral Singh
 * Created Date	:- 17-Nov-2018
 * Modified By 	:- 
 * Modified Date:- 
 * 
 * 
 */

package jameel.banKChalo.baseSetup;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import jameel.banKChalo.customListeners.CustomListeners;
import jameel.banKChalo.testUtils.APIClient;
import jameel.banKChalo.testUtils.DriverManager;
import jameel.banKChalo.testUtils.ExtentManager;
import jameel.banKChalo.testUtils.FiloReader;
import jameel.banKChalo.testUtils.JSONReader;
import jameel.banKChalo.testUtils.TestRailIntegrator;
import jameel.banKChalo.testUtils.TestUtilities;

@Listeners(CustomListeners.class)
public class InitialTest {
	public static WebDriver driver;
	public static ExtentReports extent;
	private static String testRunId;
	public static ThreadLocal<ExtentTest> classLevelReport = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelReport = new ThreadLocal<ExtentTest>();
	public static APIClient testRail;
	public static Properties property;
	JSONReader json = new JSONReader();
	public static Logger logger;
	private static String className;

	@BeforeSuite
	public void setUpResources() {
		// Load Config Files
		property = TestUtilities.loadConfigProperties();
		
		//Instantiate Extent Reports for reporting the execution status
		extent = ExtentManager.getExtent();
		
		//SetUp Test Rail Connection
		testRail = TestRailIntegrator.setUpTestRail();
		
		//Creates a new Test Run in the Test Rail
		testRunId = TestRailIntegrator.createTestRun();

	}

	@BeforeClass
	public void startClass() {
		
		//Creates a test Node at class level in the extent report
		ExtentTest parent = extent.createTest(getClass().getSimpleName());
		parent.assignCategory("Epic_Level_Report");
		classLevelReport.set(parent);
		
		//Instantiates loggers at class level
		className = this.getClass().getSimpleName().toString();
		logger = Logger.getLogger(className);
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/resources/propertyFiles/log4j.properties");

		
	}

	@BeforeMethod
	public void startMethod(Method m) {
		//Creates a test Node at class level in the extent report
		ExtentTest test = classLevelReport.get().createNode(m.getName());
		test.assignCategory("Test_Level_Report");
		testLevelReport.set(test);
		
		//Instantiates loggers at test level
		logger = Logger.getLogger(className + "---" + m.getName());
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/resources/propertyFiles/log4j.properties");
		
		
		//Invokes browser
		driver = DriverManager.getDriverInstance("chrome", 20);
		
		//Embed WebDriver listeners into the driver
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		CustomListeners listen = new CustomListeners();
		edriver.register(listen);
		driver = edriver;
		driver.get(property.getProperty("url"));

	}

	@AfterMethod
	public void killMethod(Method m, ITestResult result) {
		DriverManager.killDriverInstance();
		extent.flush();
	}
	
	
	
	@AfterClass
	public void killClass() {
		
	}
	
	@AfterSuite
	public void killResources() {
		
	}

	//data provider to provide data to the test methods
	@DataProvider(name = "dataProviderOmi")
	public Object[][] data(Method m) throws FilloException {
		int j = 0;
		Recordset record;
		Object[][] data = null;

		FiloReader filo = new FiloReader();
		filo.createConnection();
		System.out.println(m.getName());
		Recordset recordSet = filo.executeQuery("Select * from TestData where TC_ID='" + m.getName() + "'");

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

	@DataProvider(name = "dataProviderViral")
	public Object[][] dataProvider(Method m) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		return json.getData(System.getProperty("user.dir") + "/resources/jsonFiles/"
				+ m.getDeclaringClass().getName().toString().split("\\.")[3] + "/" + m.getName().toString() + ".json");
	}

	//Gets the newly created test run id
	public static String getTestRunId() {
		return testRunId;
	}

}
