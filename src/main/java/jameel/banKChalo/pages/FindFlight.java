package jameel.banKChalo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import jameel.banKChalo.pageElements.FindFlightElements;

public class FindFlight extends FindFlightElements{
	
	public void selectTripType(String tripType) {
		
		switch(tripType){
		
		case "OneWay":
			//WebElement oneWay=driver.findElement(radOneWayTrip);
			if(!radOneWayTrip.isSelected())
				radOneWayTrip.click();
				
			break;			
			
		case "Round":
			//WebElement round=driver.findElement(radRoundTrip);
			if(!radRoundTrip.isSelected())
				radRoundTrip.click();
			break;
	
		
		default:
			System.out.println("Please select a valid trip type");
		}
	}
	
	public void selectServiceClass(String serviceClass) {
		
		switch(serviceClass){
		
		case "Economy":
			//WebElement ecoClass=driver.findElement(radEconomyClass);
			if(!radEconomyClass.isSelected())
				radEconomyClass.click();
			break;		
			
		case "Business":
			//WebElement busClass=driver.findElement(radBusinessClass);
			if(!radBusinessClass.isSelected())
				radBusinessClass.click();
			break;
			
		case "First":
			//WebElement firClass=driver.findElement(radFirstClass);
			if(!radFirstClass.isSelected())
				radFirstClass.click();
			break;
		
		default:
			System.out.println("Please select a valid Class");
		}
	}
	
	public SelectFlights findFlights() {
		btnFindFlights.click();
		return (SelectFlights)openPage(SelectFlights.class);
	}

	@Override
	public ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
