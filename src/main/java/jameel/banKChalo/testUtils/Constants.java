package jameel.banKChalo.testUtils;

public class Constants {
	
	private static final String CHROME_PATH = System.getProperty("user.dir")+"/resources/executables/chromedriver.exe";
	private static final String IE_PATH=System.getProperty("user.dir")+"/resources/executables/IEDriver.exe";
	private static final String FIREFOX_PATH=System.getProperty("user.dir")+"/resources/executables/geckodriver.exe";
	private static final String CONFIG = System.getProperty("user.dir")+"/resources/propertyFiles/config.properties";
	private static final String EXTENT_CONFIG_PATH =System.getProperty("user.dir")+"/resources/extentConfig/ReportsConfig.xml";
	
	public static String getChromePath() {
		return CHROME_PATH;
	}
	
	public static String getFirefoxPath() {
		return FIREFOX_PATH;
	}
	
	public static String getIEPath() {
		return IE_PATH;
	}
	
	public static String getConfigPath() {
		return CONFIG;
	}
	
	public static  String getExtentConfigPath() {
		return EXTENT_CONFIG_PATH;
	}

}
