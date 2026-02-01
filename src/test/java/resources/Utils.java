package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification req;

	public RequestSpecification get_Request_Spec() throws FileNotFoundException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

			req = new RequestSpecBuilder().addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setBaseUri(getProperties("baseUrl"))
					.setContentType(ContentType.JSON).addQueryParam("qa", "click123").build();
			return req;
		}
		return req;
	}

	public ResponseSpecification get_Response_Spec() {
		return new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}

	public String getProperties(String key) {
		Properties prop = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream(
					"/Users/yazharasiprabhakaran/eclipse-workspace/RestAPI_BDDFramework2026/src/test/java/resources/global.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}
