package jameel.banKChalo.customListeners;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import jameel.banKChalo.baseSetup.InitialTest;
import jameel.banKChalo.testUtils.APIClient;
import jameel.banKChalo.testUtils.APIException;
import jameel.banKChalo.testUtils.TestRaiIntegrator;
import jameel.banKChalo.testUtils.TestUtilities;

public class CustomListeners extends InitialTest implements ITestListener {

	public void onTestStart(ITestResult result) {
		testLevelReport.get().log(Status.INFO, result.getMethod().getMethodName().toUpperCase() + " Execution Started");
	}


	public void onTestSuccess(ITestResult result) {
		testLevelReport.get().log(Status.INFO, result.getMethod().getMethodName().toUpperCase() + " Execution Ended");
		testLevelReport.get().log(Status.PASS, "PASSED");
		TestRaiIntegrator.addStatus(result);
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		TestUtilities.captureScreenshot(result.getName());
		String exceptionMessage = result.getThrowable().getClass().toString();

		testLevelReport.get()
				.debug("<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
						+ "Exception Occured:Click to see </summary>" + "</font>" + "</b >"
						+ exceptionMessage.replaceAll(",", "<br>") + "<br><a href =screenshots/failed_screen"
						+ " target=\"_blank\"><img src =\"screenshots/failed_screen"
						+ "\" height=\"42\" width \"42\"/></a>" + "</details>");
		testLevelReport.get().log(Status.INFO, result.getMethod().getMethodName().toUpperCase() + " Execution Ended");
		testLevelReport.get().log(Status.FAIL, "FAILED");
		TestRaiIntegrator.addStatus(result);

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

		testLevelReport.get().debug(result.getMethod().getMethodName().toUpperCase() + " isSkipped");
		testLevelReport.get().log(Status.SKIP, "SKIPPED");
		TestRaiIntegrator.addStatus(result);

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

}
