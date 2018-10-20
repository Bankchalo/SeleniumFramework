package jameel.banKChalo.testUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtilities {
	
	WebDriver driver=DriverManager.getDriver();
	
	
	
	public static void captureScreenshot(String testName) {
		File scrFile =((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String screenshotName=testName+"_"+d.toString().replaceAll("[ :]", "_")+".png";
		String absoluteScreenshotPath=Constants.getScreenshotPath()+"failed_screen.png";
		
		try {
			FileUtils.copyFile(scrFile, new File(absoluteScreenshotPath));
			FileUtils.copyFile(scrFile, new File(Constants.getArchivedScreenshotPath()+screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
