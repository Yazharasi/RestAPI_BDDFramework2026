package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
	TestDataBuild data = new TestDataBuild();
	static String place_id;

	@Given("Add Place payload is given with {string} {string} {string}")
	public void add_place_payload_is_given_with(String string, String string2, String string3)
			throws FileNotFoundException {
		

		reqSpec = given().spec(get_Request_Spec()).body(data.addPlace_RequestPayload(string, string2, string3));

	}

	@When("user calls {string} with HTTP {string} request")
	public void user_calls_with_http_post_request(String resourceName, String method) throws Exception {

		APIResources resourceAPI = APIResources.valueOf(resourceName);

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
		String respValue = getJsonPathValue(response, key);
		assertEquals(value, respValue);

	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) throws Exception {
		place_id = getJsonPathValue(response, "place_id");
		reqSpec = given().spec(get_Request_Spec()).queryParam("place_id",place_id);
		user_calls_with_http_post_request(resourceName, "Get");
		String name = getJsonPathValue(response, "name");
		assertEquals(expectedName, name);
	}
	
	@Given("DeletePlaceAPI payload")
	public void delete_place_api_payload() throws FileNotFoundException {
		reqSpec = given().spec(get_Request_Spec()).body(data.deletePlace_RequestPayload(place_id));
	}


}












