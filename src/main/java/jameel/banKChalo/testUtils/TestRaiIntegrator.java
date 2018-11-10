package jameel.banKChalo.testUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.IClass;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import jameel.banKChalo.customListeners.UseAsTestRailId;

public class TestRaiIntegrator {
	static APIClient client ;
	static Map<String, Comparable> data = new HashMap<String, Comparable>();
	static Method testMethod = null;
	static String TestID = null;
	public static APIClient setUpTestRail()
	{
		client = new APIClient("https://bankchalo.testrail.io");
		client.setUser("babak.payvandi_uat@rav-4.cf");
		client.setPassword("Windows@123");
		return client;
	}

	public static void addStatus(ITestResult result)
	{
		IClass obj = result.getTestClass();
		Class<?> newobj = obj.getRealClass();
		
		try {
			System.out.println(result.getName());
			testMethod = newobj.getDeclaredMethod(result.getName(),HashMap.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if (testMethod.isAnnotationPresent(UseAsTestRailId.class)) {
			if(result.getStatus() == ITestResult.SUCCESS)
			{
				
				data.put("status_id", new Integer(1));
				data.put("comment", "Automated Test Run Result is as Expected");
			}
			else if(result.getStatus() == ITestResult.FAILURE)
			{
				data.put("status_id", new Integer(5));
				data.put("comment", "Automated Test Run Result is not as Expected");
			}
			else if(result.getStatus() == ITestResult.SKIP)
			{
				data.put("status_id", new Integer(3));
				data.put("comment", "This case is not tested");
			}
		
			UseAsTestRailId useAsTestName = testMethod.getAnnotation(UseAsTestRailId.class);
			//Added logic for different manual cases pointing to single automation case
			for(int i=0;i<useAsTestName.testRailId().length;i++)
			{
			TestID =useAsTestName.testRailId()[i];// Integer.toString(useAsTestName.testRailId());
			System.out.println("Test Rail ID = " + TestID);
			//}
		try {
			JSONObject r = (JSONObject) client.sendPost("add_result/"+TestID, data);
		} catch (IOException | APIException e) {
			e.printStackTrace();
		}
			}
		}
			else
			{
				System.out.println("You have not given the testrail testcase id for this user");
			}
		
	}
	
}
