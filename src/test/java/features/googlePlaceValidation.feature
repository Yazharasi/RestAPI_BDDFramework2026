Feature: Validating add new place

Scenario Outline: Verify if the place is being added successfully using AddPlaceAPI

	Given Add Place payload is given with "<name>" "<address>" "<language>"
	When user calls "addPlaceAPI" with HTTP "Post" Request
	Then API call is sucess with Status code 200
	And "status" in response message is "OK"
	And "scope" in response message is "APP"
	
Examples:
	|name 				|address			|language	|
	|Mahi Textiles  	|202, Nehru Drive	|English	|
	|Hina Shoe Mart  	|147, Tidel Park	|Tamil		|
	|Tina Jewellers  	|413, Avalon Centre	|Thai		|

