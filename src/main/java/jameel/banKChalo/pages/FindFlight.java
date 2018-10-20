package jameel.banKChalo.pages;

import org.openqa.selenium.WebElement;

import jameel.banKChalo.pageElements.FindFlightElements;

public class FindFlight extends FindFlightElements{
	
	public void selectTripType(String tripType) {
		
		switch(tripType){
		
		case "OneWay":
			WebElement oneWay=driver.findElement(radOneWayTrip);
			if(!oneWay.isSelected())
				oneWay.click();
			break;			
			
		case "Round":
			WebElement round=driver.findElement(radRoundTrip);
			if(!round.isSelected())
				round.click();
			break;
	
		
		default:
			System.out.println("Please select a valid trip type");
		}
	}
	
	public void selectServiceClass(String serviceClass) {
		
		switch(serviceClass){
		
		case "Economy":
			WebElement ecoClass=driver.findElement(radEconomyClass);
			if(!ecoClass.isSelected())
				ecoClass.click();
			break;			
			
		case "Business":
			WebElement busClass=driver.findElement(radBusinessClass);
			if(!busClass.isSelected())
				busClass.click();
			break;
			
		case "First":
			WebElement firClass=driver.findElement(radFirstClass);
			if(!firClass.isSelected())
				firClass.click();
			break;
		
		default:
			System.out.println("Please select a valid Class");
		}
	}
	
	public SelectFlights findFlights() {
		driver.findElement(btnFindFlights).click();
		return new SelectFlights();
	}

}
