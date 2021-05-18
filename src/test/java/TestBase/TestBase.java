package test.java.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestBase {

	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static String bearer_token;
	public static Response response;

	public void init() {
		try {
			fis = new FileInputStream(".\\src\\test\\resources\\Properties\\config.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			config.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RestAssured.baseURI = config.getProperty("baseURI");
		RestAssured.basePath = config.getProperty("basePath");
		// System.out.println(RestAssured.baseURI+RestAssured.basePath);

		bearer_token = config.getProperty("validAccessKey");
	}

}
