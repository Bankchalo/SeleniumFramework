package jameel.banKChalo.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import jameel.banKChalo.pages.BasePage;

public abstract class FindFlightElements extends BasePage{
	
/*	protected By radOneWayTrip = By.xpath("//input[@name='tripType' and @value='oneway']");
	protected By radRoundTrip = By.xpath("//input[@name='tripType' and @value='roundtrip']");
	protected By radEconomyClass=By.xpath("//input[@name='servClass' and @value='Coach']");
	protected By radBusinessClass=By.xpath("//input[@name='servClass' and @value='Business']");
	protected By radFirstClass=By.xpath("//input[@name='servClass' and @value='First']");
	
	protected By btnFindFlights = By.xpath("//input[@name='findFlights']");*/
	
	@FindBy(xpath="//input[@name='tripType' and @value='oneway']")
	protected WebElement radOneWayTrip;
	
	@FindBy(xpath="//input[@name='tripType' and @value='roundtrip']")
	protected WebElement radRoundTrip;
	
	@FindBy(xpath="//input[@name='servClass' and @value='Coach']")
	protected WebElement radEconomyClass;
	
	@FindBy(xpath="//input[@name='servClass' and @value='Business']")
	protected WebElement radBusinessClass;
	
	@FindBy(xpath="//input[@name='servClass' and @value='First']")
	protected WebElement radFirstClass;
	
	@FindBy(xpath="//input[@name='findFlights']")
	protected WebElement btnFindFlights;

}
