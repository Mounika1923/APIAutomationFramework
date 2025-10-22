Feature: Test the Add/update/Delete place API

@AddPlace
Scenario Outline: validate the Add Place Api
Given valid "POST" request payload "<name>" "<language>" "<address>" "<addressToUpdate>"
When submit the "AddPlaceAPI" with "POST" request
Then validate whether the reponse is 200
And Fetch the place_id from the reponse
Then validate the "status" field of the response body is "OK"
Then validate the "scope" field of the response body is "APP"
And verify "name" created maps to "<name>" using "GetPlaceAPI"


@AddPlace
Scenario: Validate the Update place API
Given valid "PUT" request payload "<name>" "<language>" "<address>" "<addressToUpdate>"
When submit the "UpdatePlaceAPI" with "PUT" request
Then validate whether the reponse is 200
Then validate the "msg" field of the response body is "Address successfully updated"


@AddPlace
Scenario: Validate the Delete place API
Given valid "DELETE" request payload "<name>" "<language>" "<address>" "<addressToUpdate>"
When submit the "DeletePlaceAPI" with "DELETE" request
Then validate whether the reponse is 200
Then validate the "status" field of the response body is "OK"

@AddPlace
Scenario: validate the Add Place Api with wrong test data
Given valid "POST" request payload "<name>" "<language>" "<address>" "<addressToUpdate>"
When submit the "wrongAddPlaceAPI" with "POST" request wrong test data
Then validate whether the reponse is 404

Examples:
|  name | language | address  |addressToUpdate|
|rainbowpg| kannada|banglore| america|
#|mounisweets|Tamil|Chennai|Australia|
