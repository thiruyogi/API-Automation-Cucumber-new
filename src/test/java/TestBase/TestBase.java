package test.java.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestBase {

	public static Properties config = new Properties();
	public static FileInputStream fis;
	//public static InputStream inputStream;
	public static String bearer_token;
	public static Response response;

	public void init() throws FileNotFoundException {
		String fileName = "config.properties";
		 //inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Properties/"+fileName);
		try {
			config.load(fis);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RestAssured.baseURI = config.getProperty("baseURI");
		RestAssured.basePath = config.getProperty("basePath");
		System.out.println(RestAssured.baseURI+RestAssured.basePath);

		bearer_token = config.getProperty("validAccessKey");
	}

}
