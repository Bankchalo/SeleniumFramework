package jameel.banKChalo.customListeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import jameel.banKChalo.baseSetup.InitialTest;
import jameel.banKChalo.testUtils.TestRaiIntegrator;
import jameel.banKChalo.testUtils.TestUtilities;

public class CustomListeners extends InitialTest implements ITestListener,WebDriverEventListener {

	public void onTestStart(ITestResult result) {
		testLevelReport.get().log(Status.INFO, result.getMethod().getMethodName().toUpperCase() + " Execution Started");
		logger.info(result.getMethod().getMethodName().toUpperCase() + " Execution Started");
	}


	public void onTestSuccess(ITestResult result) {
		testLevelReport.get().log(Status.INFO, result.getMethod().getMethodName().toUpperCase() + " Execution Ended");
		testLevelReport.get().log(Status.PASS, "PASSED");
		TestRaiIntegrator.addStatusForCase(result);
		logger.info(result.getMethod().getMethodName().toUpperCase() + " Execution Ended");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		TestUtilities.captureScreenshot(result.getName());
		String exceptionMessage = result.getThrowable().getClass().toString();

		testLevelReport.get()
				.debug("<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
						+ "Exception Occured:Click to see </summary>" + "</font>" + "</b >"
						+ exceptionMessage.replaceAll(",", "<br>") + "<br><a href =screenshots/failed_screen.png"
						+ " target=\"_blank\"><img src =\"screenshots/failed_screen.png"
						+ "\" height=\"100\" width =\"150\"/></a>" + "</details>");
		testLevelReport.get().log(Status.INFO, result.getMethod().getMethodName().toUpperCase() + " Execution Ended");
		testLevelReport.get().log(Status.FAIL, "FAILED");
		TestRaiIntegrator.addStatusForCase(result);
		logger.info(result.getMethod().getMethodName().toUpperCase() + " Execution Ended");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

		testLevelReport.get().debug(result.getMethod().getMethodName().toUpperCase() + " isSkipped");
		testLevelReport.get().log(Status.SKIP, "SKIPPED");
		TestRaiIntegrator.addStatusForCase(result);
		logger.info("result.getMethod().getMethodName().toUpperCase() + \" Execution Ended\"");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
//#############################################################################################################

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
