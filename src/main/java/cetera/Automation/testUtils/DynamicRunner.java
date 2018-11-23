/**
 * ClassName :- DynamicRunner
 * This class creates a testng xml file programmatically.
 * 
 * Created By 	:- Umesh Joshi/Viral Singh
 * Created Date :- 17-Nov 2018
 * Modified By 	:- 
 * Modified Date:- 
 *
 */

package cetera.Automation.testUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DynamicRunner {
	
	public static String suiteName;
	
	
	@Test
	public void createTestRunner() {
		JsonObject obj	=JSONReader.readJson(Constants.getRunnerJsonPath());
		JsonArray jsonArray =(JsonArray)obj.get("Suites");
		
		//Create a testNG istance to run the testNg.xml
		TestNG testNG = new TestNG();
		
		//testNG.addListener(CustomListeners.class);
		
		//Create a XmlSuite instance to create a suite node
		List<XmlSuite> suites = new ArrayList<>();
		
		
		////suite.addListener("cetera.Automation.customListeners.CustomListeners");
		//suite.addListener("cetera.Automation.customListeners.WebDriverListeners");
		
		//suite.setFileName("Sample");
		
		//Create a XmlTest instance to create a test node
		

		////test.setName("My Test");
		
		
		//Create a list of XmlTest to store multiple test tags.

		

		
		//Create a list of XmlClass to store multiple class tags.
		
		
		
		//The list will contain all the methods that are to be included
		List<XmlInclude> methodsToInclude=null;
		
		
		//Create a XmlClass instance to create a class node
		
		
		//Includes all the enabled classes	
		for(JsonElement suiteElement:jsonArray) {
			List<XmlClass> myClasses = new ArrayList<> ();
			List<XmlTest> tests = new ArrayList<>();
			JsonObject suiteObj = suiteElement.getAsJsonObject();
			suiteName=String.valueOf(suiteObj.get("SuiteName")).replace("\"", "");
			String testName=String.valueOf(suiteObj.get("TestName")).replace("\"", "");
			JsonArray classArray = (JsonArray) suiteObj.get("Classes");
			
			XmlSuite suite = new XmlSuite();
			suite.setName(suiteName);
			suite.addListener("cetera.Automation.customListeners.CustomListeners");
			XmlTest test = new XmlTest(suite);
			test.setName(testName);
			for(JsonElement classElement:classArray) {
						
				XmlClass class1=new XmlClass();
				methodsToInclude=new ArrayList<>();
				JsonObject classObj = classElement.getAsJsonObject();
				boolean isClassEnabled=classObj.get("isEnabled").getAsBoolean();
				if(isClassEnabled) {
					class1.setName("cetera.Automation.tests."+String.valueOf(classObj.get("ClassName")).replace("\"", ""));
					JsonArray methodArray = (JsonArray) classObj.get("Methods");
					
					//Includes all the enabled methods within the class
					for(JsonElement methodElement:methodArray) {
						
						JsonObject methodObj = methodElement.getAsJsonObject();
						boolean isMethodEnabled=methodObj.get("isEnabled").getAsBoolean();
						if(isMethodEnabled)
							methodsToInclude.add(new XmlInclude(String.valueOf(methodObj.get("testCaseName")).replace("\"", "")));
					}
					
					//add the included groups inside the class
					class1.setIncludedMethods(methodsToInclude);
					
					//add the class inside classes tag
					myClasses.add(class1);
			
				}	
					
				}
			test.setXmlClasses(myClasses);
			tests.add(test);
			suite.setTests(tests);
			suites.add(suite);
			File file = new File("./testNG_Files"+suiteName+".xml");
		    FileWriter write;
			try {
				write = new FileWriter(file);
				write.write(suite.toXml());
				write.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		//add all the tests inside test tag
		

		
		
		
		//System.out.println(suite.toXml());
	    testNG.setXmlSuites(suites);

	    
		testNG.run();	    

	    
	}
	
	
}