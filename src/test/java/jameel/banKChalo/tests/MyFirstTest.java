package jameel.banKChalo.tests;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.annotations.Test;

import jameel.banKChalo.baseSetup.InitialTest;
import jameel.banKChalo.customListeners.UseAsTestCaseId;
import jameel.banKChalo.customListeners.UseAsTestRailId;
import jameel.banKChalo.pages.FindFlight;
import jameel.banKChalo.pages.LandingPage;
import jameel.banKChalo.pages.SelectFlights;
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