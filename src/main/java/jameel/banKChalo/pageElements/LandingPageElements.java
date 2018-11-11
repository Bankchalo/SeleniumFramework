package jameel.banKChalo.pageElements;

import org.openqa.selenium.By;

import jameel.banKChalo.pages.BasePage;


public class LandingPageElements extends BasePage{
	
	protected By elmUserName = By.name("userName");
	protected By elmPassword = By.name("password");
	protected By elmLoginButton = By.xpath("//input[@name='login']");
}
