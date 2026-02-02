package resources;

import java.util.Arrays;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
public AddPlace addPlace_RequestPayload(String name, String address, String language) {
	Location loc = new Location();
	loc.setLat("-38.383414");
	loc.setLng("33.427360");

	String[] types = { "shoe park", "shop" };
	List<String> type = Arrays.asList(types);

	AddPlace ap = new AddPlace();
	ap.setAccuracy(109);
	ap.setAddress(address);
	ap.setLanguage(language);
	ap.setLocation(loc);
	ap.setName(name);
	ap.setPhone_number("(+91) 983 893 3937");
	ap.setTypes(type);
	ap.setWebsite("http://rahulshettyacademy.com");
	
	return ap;
}

public String deletePlace_RequestPayload(String place_id) {
	return "{\n"
			+ "    \"place_id\":\""+place_id+"\"\n"
			+ "}";
}
}
