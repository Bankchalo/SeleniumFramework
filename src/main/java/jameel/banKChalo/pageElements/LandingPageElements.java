package jameel.banKChalo.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import jameel.banKChalo.pages.BasePage;


public abstract class LandingPageElements extends BasePage{
	
	/*protected By elmUserName = By.name("userName");
	protected By elmPassword = By.name("password");
	protected By elmLoginButton = By.xpath("//input[@name='login']");*/
	
	@FindBy(name="userName")
	protected WebElement elmUserName;
	
	@FindBy(name="password")
	protected WebElement elmPassword;
	
	@FindBy(xpath="//input[@name='login']")
	protected WebElement btnLoginButton;
	
	
}
