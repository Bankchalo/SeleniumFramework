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
	@UseAsTestCaseId(testCaseId= 11)
	public void loginWithValidCredentials(Hashtable<String,String> data) {
		LandingPage lpage = new LandingPage().open();
		FindFlight hPage = lpage.login(data.get("USERNAME"),data.get("PASSWORD"));
		hPage.selectTripType(data.get("TRIP_TYPE"));
		hPage.selectServiceClass(data.get("SERVICE_CLASS"));
		SelectFlights sFlight = hPage.findFlights();
		Assert.fail();
	}
	
	@Test(dataProvider="dataProviderOmi")
	@UseAsTestCaseId(testCaseId=10)
	public void loginWithValidCredentialsViral(Hashtable<String,String> data) {
		
		LandingPage lpage = new LandingPage().open();
		FindFlight hPage = lpage.login(data.get("USERNAME"),data.get("PASSWORD"));
		hPage.selectTripType(data.get("TRIP_TYPE"));
		hPage.selectServiceClass(data.get("SERVICE_CLASS"));
		SelectFlights sFlight = hPage.findFlights();
	}
}