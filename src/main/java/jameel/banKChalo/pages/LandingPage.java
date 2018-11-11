package jameel.banKChalo.pages;

import jameel.banKChalo.pageElements.LandingPageElements;

public class LandingPage extends LandingPageElements{
	
	
	public FindFlight login(String userName,String password) {
		
		driver.findElement(elmUserName).sendKeys(userName);
		
		driver.findElement(elmPassword).sendKeys(password);
		
		driver.findElement(elmLoginButton).click();
		
		return new FindFlight();	
	}

}
