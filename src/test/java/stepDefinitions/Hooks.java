package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws Exception {
		if(StepDefinition.place_id == null){
		StepDefinition sd = new StepDefinition();
		sd.add_place_payload_is_given_with("Mira Bangles", "140, West Drive", "Spanish");
		sd.user_calls_with_http_post_request("addPlaceAPI", "post");
		StepDefinition.place_id = sd.getJsonPathValue(sd.response, "place_id");
		}
	}

}
