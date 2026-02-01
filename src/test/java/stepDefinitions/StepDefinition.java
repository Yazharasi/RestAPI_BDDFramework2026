package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;

	@Given("Add Place payload is given with {string} {string} {string}")
	public void add_place_payload_is_given_with(String string, String string2, String string3)
			throws FileNotFoundException {
		TestDataBuild data = new TestDataBuild();

		reqSpec = given().spec(get_Request_Spec()).body(data.addPlace_RequestPayload(string, string2, string3));

	}

	@When("user calls {string} with HTTP {string} Request")
	public void user_calls_with_http_post_request(String resource, String method) throws Exception {

		APIResources resourceAPI = APIResources.valueOf(resource);

		if (method.equalsIgnoreCase("POST")) {
			response = reqSpec.when().post(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = reqSpec.when().get(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("PUT")) {
			response = reqSpec.when().put(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("DELETE")) {
			response = reqSpec.when().delete(resourceAPI.getResource());
		} else {
			throw new Exception("The given HTTP method is incorrect!");
		}

	}

	@Then("API call is sucess with Status code {int}")
	public void api_call_is_sucess_with_status_code(int int1) {
		int statCode = response.getStatusCode();
		assertEquals(statCode, int1);

	}

	@Then("{string} in response message is {string}")
	public void in_response_message_is(String key, String value) {
		String respString = response.asString();
		JsonPath js = new JsonPath(respString);
		assertEquals(js.getString(key), value);

	}

}




