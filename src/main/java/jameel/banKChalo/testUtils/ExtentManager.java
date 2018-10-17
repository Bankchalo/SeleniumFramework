package jameel.banKChalo.testUtils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath=System.getProperty("user.dir")+"/testResults/extentReports/extentReport.html";
	
	public static ExtentReports getExtent() {
		if(extent!=null) {
			return extent;
		}else {
			extent=new ExtentReports();
			extent.attachReporter(getHtmlReporter());
			extent.setSystemInfo("Owner", "Jameel");
			extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
			return extent;
		}
	}
	
	
	public static ExtentHtmlReporter getHtmlReporter() {
		System.out.println(filePath);

		htmlReporter = new ExtentHtmlReporter(filePath);
		/*htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("IdentHQ Automation Report");
		htmlReporter.config().setReportName("Automation");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);*/
		
		htmlReporter.setAppendExisting(false);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/resources/extentConfig/ReportsConfig.xml");

		return htmlReporter;
	}

}
