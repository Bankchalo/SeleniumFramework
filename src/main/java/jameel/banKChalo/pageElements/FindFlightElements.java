package jameel.banKChalo.pageElements;

import org.openqa.selenium.By;

import jameel.banKChalo.baseSetup.InitialTest;

public class FindFlightElements extends InitialTest{
	
	protected By radOneWayTrip = By.xpath("//input[@name='tripType' and @value='oneway']");
	protected By radRoundTrip = By.xpath("//input[@name='tripType' and @value='roundtrip']");
	protected By radEconomyClass=By.xpath("//input[@name='servClass' and @value='Coach']");
	protected By radBusinessClass=By.xpath("//input[@name='servClass' and @value='Business']");
	protected By radFirstClass=By.xpath("//input[@name='servClass' and @value='First']");
	
	protected By btnFindFlights = By.xpath("//input[@name='findFlights']");

}
