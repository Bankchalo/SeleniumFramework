package jameel.banKChalo.tests;

import java.util.HashMap;

import org.testng.annotations.Test;

public class MyFirstTest {
	
	@Test(dataProvider="dataProvider")
	public void iPhone(HashMap<String,String> data)
	{
		System.out.println(data.get("TestCaseName"));
		System.out.println(data.get("TestCaseID"));
	}

}
