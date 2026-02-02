Feature: Validating Google MAP APIs

@AddPlace
Scenario Outline: Verify if the place is being added successfully using AddPlaceAPI

	Given Add Place payload is given with "<name>" "<address>" "<language>"
	When user calls "addPlaceAPI" with HTTP "Post" request
	Then API call is sucess with Status code 200
	And "status" in response message is "OK"
	And "scope" in response message is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name 				|address			|language	|
	|Mahi Textiles  	|202, Nehru Drive	|English	|
#	|Hina Shoe Mart  	|147, Tidel Park	|Tamil		|
#	|Tina Jewellers  	|413, Avalon Centre	|Thai		|


@DeletePlace
Scenario: Verify if the Delete place API is functioning correctly

	Given DeletePlaceAPI payload
	When user calls "deletePlaceAPI" with HTTP "Delete" request
	Then API call is sucess with Status code 200
	And "status" in response message is "OK"
	
