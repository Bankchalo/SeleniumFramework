package cetera.Automation.customListeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import cetera.Automation.baseSetup.InitialTest;

public class WebDriverListeners extends InitialTest implements WebDriverEventListener{
	
	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		testLevelReport.get().debug("Alert accepted on page "+driver.getTitle());
		logger.info("Alert accepted on page "+driver.getTitle());
	}


	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		testLevelReport.get().debug("Alert rejected on page "+driver.getTitle());
		logger.info("Alert rejected on page "+driver.getTitle());
	}


	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] text) {
		// TODO Auto-generated method stub
		testLevelReport.get().debug("Entered \""+element.getAttribute("value")+"\" into the text box "+element.toString().split(":")[2].replace("]", ""));
		logger.info("Entered \""+element.getAttribute("value")+" into the text box "+element.toString().split(":")[2].replace("]", ""));
	}


	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		testLevelReport.get().debug("Clicked on the element having xpath : "+element.toString().split(":")[2].replace("]", ""));
		logger.info("Clicked on the element having xpath : "+element.toString().split(":")[2].replace("]", ""));
	}


	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		testLevelReport.get().debug("Navigated back to "+driver.getTitle());
		logger.info("Navigated back to "+driver.getTitle());
		
	}


	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		testLevelReport.get().debug("Navigated to "+driver.getTitle());
		logger.info("Navigated to "+driver.getTitle());
	}


	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		testLevelReport.get().debug(driver.getTitle()+" Page refreshed");
		logger.info(driver.getTitle()+" Page refreshed");
	}


	@Override
	public void afterNavigateTo(String arg0, WebDriver driver) {
		// TODO Auto-generated method stub
		testLevelReport.get().debug("Navigated to "+driver.getTitle());
		logger.info("Navigated to "+driver.getTitle());
	}


	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] text) {
		// TODO Auto-generated method stub
		testLevelReport.get().debug("Trying to enter text into the text box "+element.toString().split(":")[2].replace("]", ""));
		logger.info("Trying to enter text into the text box "+element.toString().split(":")[2].replace("]", ""));
	}


	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		testLevelReport.get().debug("Trying to click on the element having xpath : "+element.toString().split(":")[2].replace("]", ""));
		logger.info("Trying to click on the element having xpath : "+element.toString().split(":")[2].replace("]", ""));
	}


	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

}
