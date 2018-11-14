package jameel.banKChalo.testUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.IClass;
import org.testng.ITestResult;

import jameel.banKChalo.baseSetup.InitialTest;
import jameel.banKChalo.customListeners.UseAsTestCaseId;
import jameel.banKChalo.customListeners.UseAsTestRailId;

public class TestRaiIntegrator extends InitialTest{
	static APIClient client ;
	static Map<String, Comparable> data = new HashMap<String, Comparable>();
	
	//static String testRunId;
	
	
		
	public static APIClient setUpTestRail()
	{
		client = new APIClient("https://bankchalo.testrail.io");
		client.setUser("babak.payvandi_uat@rav-4.cf");
		client.setPassword("Windows@123");
		return client;
	}

	/*public static void addStatus(ITestResult result)
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
		}
			UseAsTestRailId useAsTestName = testMethod.getAnnotation(UseAsTestRailId.class);
			String TestID = Integer.toString(useAsTestName.testRailId());
			System.out.println("Test Rail ID = " + TestID);
			//}
		try {
			JSONObject r = (JSONObject) client.sendPost("add_result/"+TestID, data);
		} catch
		(IOException | APIException e) {
			e.printStackTrace();
		}
		
	}*/
	
	public static void addStatusForCase(ITestResult result)
	{
		Method testMethod=null;
		String testRunId=null;
		if(testRunId==null)
			testRunId=InitialTest.property.getProperty("testRunId");
	
		IClass obj = result.getTestClass();
		Class<?> newobj = obj.getRealClass();
		
		try {
			System.out.println(result.getName());
			testMethod = newobj.getDeclaredMethod(result.getName(),Hashtable.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if (testMethod.isAnnotationPresent(UseAsTestCaseId.class)) {
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
			UseAsTestCaseId useAsTestName = testMethod.getAnnotation(UseAsTestCaseId.class);
			for(int caseId:useAsTestName.testCaseId()) {
				try {
					JSONObject r = (JSONObject) client.sendPost("add_result_for_case/"+testRunId+"/"+Integer.toString(caseId), data);
					testLevelReport.get().debug("The test case having test case id "+caseId+" is executed successfully in the test Rail");
				} catch (IOException | APIException e) {
					e.printStackTrace();
				}				
			}
			
			
		}else {
			testLevelReport.get().debug("This test case is not executed in the test Rail");
		}
		
		
	}
}
