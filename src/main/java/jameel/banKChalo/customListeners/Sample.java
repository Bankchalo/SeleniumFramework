package jameel.banKChalo.customListeners;

import jameel.banKChalo.testUtils.APIClient;
import jameel.banKChalo.testUtils.TestRaiIntegrator;

public class Sample {
	public static APIClient testRail;
	public static void main(String[] args) {
		testRail=TestRaiIntegrator.setUpTestRail();
		System.out.println("sample");
		System.out.println(TestRaiIntegrator.createTestRun());	
		
	}

}
