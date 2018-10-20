package jameel.banKChalo.pageElements;

import org.openqa.selenium.By;

import jameel.banKChalo.baseSetup.InitialTest;


public class LandingPageElements extends InitialTest{
	
	protected By elmUserName = By.name("userName");
	protected By elmPassword = By.name("password");
	protected By elmLoginButton = By.xpath("//input[@name='login']");
}
