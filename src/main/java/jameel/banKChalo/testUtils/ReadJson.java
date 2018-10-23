//Do Not Delete this file

package jameel.banKChalo.testUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.beust.testng.TestNG;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ReadJson {
	
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
		JsonArray arr=readJson();
		createTestRunner(arr);
	}
	
	public static void createTestRunner(JsonArray jsonArray) {
		
		TestNG testNG = new TestNG();
		XmlSuite suite = new XmlSuite();
		suite.setName("My Suite");
		
		XmlTest test = new XmlTest();
		test.setName("My Test");
		
		List<XmlClass> myClasses = new ArrayList<XmlClass> ();
		
		for(JsonElement elm:jsonArray) {
			
			JsonObject arr = elm.getAsJsonObject();
			System.out.println(arr.get("ClassName"));
			myClasses.add(new XmlClass(arr.get("ClassName").getAsString()));
				
		}
		test.setXmlClasses(myClasses);
		
		List<XmlTest> tests = new ArrayList<>();
		tests.add(test);
		suite.setTests(tests);
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
	    mySuites.add(suite);
	    testNG.setXmlSuites(mySuites);
	    System.out.println(testNG.toString());
	}
	
	
}
