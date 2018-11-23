package cetera.Automation.tests;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.annotations.Test;

import cetera.Automation.baseSetup.InitialTest;
import cetera.Automation.customListeners.UseAsTestCaseId;
import cetera.Automation.customListeners.UseAsTestRailId;
import cetera.Automation.pages.FindFlight;
import cetera.Automation.pages.LandingPage;
import cetera.Automation.pages.SelectFlights;
import junit.framework.Assert;

public class MyFirstTest extends InitialTest{

	@Test(dataProvider="dataProviderOmi")
	@UseAsTestCaseId(testCaseId=12)
	public void loginWithValidCredentials(Hashtable<String,String> data) {
		LandingPage lpage = new LandingPage().open();
		FindFlight hPage = lpage.login(data.get("USERNAME"),data.get("PASSWORD"));
		Assert.assertTrue(hPage.validateLoginSuccess());
		/*hPage.selectTripType(data.get("TRIP_TYPE"));
		hPage.selectServiceClass(data.get("SERVICE_CLASS"));
		SelectFlights sFlight = hPage.findFlights();*/
		
	}
	
	@Test(dataProvider="dataProviderOmi")
	@UseAsTestCaseId(testCaseId=13)
	public void loginWithInvalidCredentials(Hashtable<String,String> data) {
		
		LandingPage lpage = new LandingPage().open();
		FindFlight hPage = lpage.login(data.get("USERNAME"),data.get("PASSWORD"));
		Assert.assertTrue(hPage.validateLoginSuccess());
		/*hPage.selectTripType(data.get("TRIP_TYPE"));
		hPage.selectServiceClass(data.get("SERVICE_CLASS"));
		SelectFlights sFlight = hPage.findFlights();*/
	}
}