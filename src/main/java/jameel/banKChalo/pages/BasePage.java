/**
 * ClassName :- BasePage
 * Initializes page factory of every class
 * 
 * Created By 	:- Umesh Joshi/Viral Singh
 * Created Date :- 17-Nov 2018
 * Modified By 	:- 
 * Modified Date:- 
 *
 */

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
		//ExpectedCondition condition = ((BasePage)page).getPageLoadCondition();
		//waitForPageLoadCondition(condition);
				
		return page;
		
	}
	
	public static void waitForPageLoadCondition(ExpectedCondition expected) {
		Wait wait = new WebDriverWait(DriverManager.getDriver(), 10);
		wait.until(expected);
	}
	
	public abstract ExpectedCondition getPageLoadCondition();
	
	

}
