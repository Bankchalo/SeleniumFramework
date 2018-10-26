//Do Not Delete this file

package jameel.banKChalo.testUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.beust.testng.TestNG;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class DynamicRunner {
	
	static JsonParser parser = new JsonParser();
	static JsonObject obj;
	//JsonObject obj =
	public static JsonArray readJson() {
		try {
			obj =parser.parse(new FileReader(Constants.getRunnerJsonPath())).getAsJsonObject();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonArray arr =(JsonArray)obj.get("Classes");
		return arr;
	}
	
	public static void main(String[] args) {
		//JsonArray arr=readJson();
		createTestRunner();
	}
	
	public static void createTestRunner() {
		
		JsonArray jsonArray	=readJson();
		//Create a testNG istance to run the testNg.xml
		TestNG testNG = new TestNG();
		
		//Create a XmlSuite instance to create a suite node
		List<XmlSuite> suites = new ArrayList<>();
		XmlSuite suite = new XmlSuite();
		suite.setName("My Suite");
		
		//Create a XmlTest instance to create a test node
		XmlTest test = new XmlTest();
		test.setName("My Test");
		
		
		//Create a list of XmlTest to store multiple test tags.
		List<XmlTest> tests = new ArrayList<>();
		
		
		
		//Create a list of XmlClass to store multiple class tags.
		List<XmlClass> myClasses = new ArrayList<> ();
		
		
		//The list will contain all the methods that are to be included
		List<XmlInclude> methodsToInclude=null;
		
		
		//Create a XmlClass instance to create a class node
		
		
		//Includes all the enabled classes		
		for(JsonElement classElement:jsonArray) {
			
			XmlClass class1=new XmlClass();
			methodsToInclude=new ArrayList<>();
			JsonObject classObj = classElement.getAsJsonObject();
			boolean isClassEnabled=classObj.get("isEnabled").getAsBoolean();
			if(isClassEnabled) {
				class1.setName("jameel.banKChalo.tests."+String.valueOf(classObj.get("ClassName")).replace("\"", ""));
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
		//add all the tests inside test tag
		test.setXmlClasses(myClasses);
		
		tests.add(test);
		suite.setTests(tests);
		suites.add(suite);
		System.out.println(suite.toXml());
	    testNG.setXmlSuites(suites);
	    File file = new File("lastTestNgXmlRun.xml");
	    FileWriter write;
		try {
			write = new FileWriter(file);
			write.write(suite.toXml());
			write.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//testNG.run();	    
	    
	}
	
	
}
