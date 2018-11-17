package jameel.banKChalo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import jameel.banKChalo.pageElements.LandingPageElements;

public class LandingPage extends LandingPageElements{
	
	
	public FindFlight login(String userName,String password) {
		//WebElement element=elmUserName	;
		elmUserName.sendKeys(userName);
		//driver.findElement(By.name("userName")).sendKeys(userName);;
		elmPassword.sendKeys(password);
		btnLoginButton.click();
		return (FindFlight)openPage(FindFlight.class);
	
	}
	
	public LandingPage open() {
		return (LandingPage)openPage(LandingPage.class);
	}

	@Override
	public ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
