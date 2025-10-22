package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class commonUtils {
	
	public static String getGlobalProperties(String key) throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Vamsi\\RESTAPIAutomationFramework\\APIAutomation\\src\\test\\java\\resources\\global.properties");
		
		 try {
	            prop.load(fis);
	            return prop.getProperty(key);
	        } finally {
	            // Ensure stream is closed
	            if (fis != null) {
	                fis.close();
	            }
	}

}
	
	public  String getJsonPathString(Response res , String key)
	{
		String responseAsString =res.asString();
		JsonPath js = new JsonPath(responseAsString);
		return js.getString(key);
	}
}
