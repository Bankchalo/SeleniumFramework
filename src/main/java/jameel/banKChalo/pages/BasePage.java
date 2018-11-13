package jameel.banKChalo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import jameel.banKChalo.baseSetup.InitialTest;
import jameel.banKChalo.testUtils.DriverManager;

public abstract class BasePage<T> extends InitialTest{
	
	public T openPage(Class<T> clazz) {
		T page=null;
		
		page=PageFactory.initElements(DriverManager.getDriver(), clazz);
		AjaxElementLocatorFactory elm = new AjaxElementLocatorFactory(DriverManager.getDriver(), 20);
				
		return page;
		
	}
	
	public void waitForPageLoadCondition(ExpectedCondition expected) {
		Wait wait = new WebDriverWait(DriverManager.getDriver(), 10);
		wait.until(expected);
	}
	
	//public abstract ExpectedCondition getPageLoadCondition();
	
	

}
