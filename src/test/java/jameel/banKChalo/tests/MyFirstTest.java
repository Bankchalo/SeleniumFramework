package jameel.banKChalo.tests;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.annotations.Test;

import jameel.banKChalo.baseSetup.InitialTest;
import jameel.banKChalo.pages.FindFlight;
import jameel.banKChalo.pages.LandingPage;
import jameel.banKChalo.pages.SelectFlights;

public class MyFirstTest extends InitialTest{

	@Test(dataProvider="dataProviderOmi")
	public void loginWithValidCredentials(Hashtable<String,String> data) {
		LandingPage lpage = new LandingPage();
		FindFlight hPage = lpage.login(data.get("USERNAME"),data.get("PASSWORD"));
		hPage.selectTripType(data.get("TRIP_TYPE"));
		hPage.selectServiceClass(data.get("SERVICE_CLASS"));
		SelectFlights sFlight = hPage.findFlights();
	}
	
	@Test(dataProvider="dataProviderViral")
	public void loginWithValidCredentialsViral(HashMap<String,String> data) {
		LandingPage lpage = new LandingPage();
		FindFlight hPage = lpage.login(data.get("USERNAME"),data.get("PASSWORD"));
		hPage.selectTripType(data.get("TRIP_TYPE"));
		hPage.selectServiceClass(data.get("SERVICE_CLASS"));
		SelectFlights sFlight = hPage.findFlights();
	}

}
