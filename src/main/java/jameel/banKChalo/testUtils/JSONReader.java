package jameel.banKChalo.testUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


public class JSONReader {
	/*public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		System.out.println(getData("C:\\Users\\VIRAL\\git\\SeleniumFramework\\resources\\test data\\Sample.json","iPhone"));
	}*/
	static JsonParser parser = new JsonParser();
	static JsonObject obj;
	
	public static JsonObject readJson(String path) {
		try {
			obj =parser.parse(new FileReader(path)).getAsJsonObject();
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
		//JsonArray arr =(JsonArray)obj.get("Classes");
		return obj;
	}
	
	public  Object[][] getData(String Json_Path) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonParser jsonParser= new JsonParser();
		JsonObject jsonObj = jsonParser.parse(new FileReader(Json_Path)).getAsJsonObject();
		JsonArray array= (JsonArray) jsonObj.get("DataSet");
		return 	searchJsonElement(array);
	}
	
	public static Object[][] searchJsonElement(JsonArray jsonArray) {
		
		HashMap<String,String> map= new HashMap();
		int i=0,j=0,col=0;
		//JsonElement jsonElement=jsonArray. jsonElement.getAsJsonObject().entrySet().size();
		Object[][] matrix= new Object[jsonArray.size()][1];
		for(JsonElement jsonElement :jsonArray)
		{
			for(Map.Entry<String,JsonElement> entry:jsonElement.getAsJsonObject().entrySet())
			{
				String key = entry.getKey().toString();
				String value= entry.getValue().toString().replace("\"", "").toString();		
				map.put(key, value);	
				/*matrix[i][0] = entry.getKey().toString();
				matrix[i][1] = entry.getValue().toString()..replace("\"", "").toString();*/
				//matrix[i][1]=map.put(entry.getKey().toString(), entry.getValue().toString().replace("\"", "").toString());				
			}
			matrix[j][0]=map;
			j++;
		}
		return matrix;
	}
	
}
