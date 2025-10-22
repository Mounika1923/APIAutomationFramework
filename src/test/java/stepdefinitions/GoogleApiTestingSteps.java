package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.GoogleApiParentPojo;
import pojoClasses.LocationObject;
import pojoClasses.UpdateGoogleAPIPojo;
import requestPayLoads.GoogleApiPayLoads;
import requestSpecifications.GoogleAPIRequestSpecifications;
import utils.GoogleAPIResourcesEnum;
import utils.commonUtils;

public class GoogleApiTestingSteps extends commonUtils {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response res;
    static String placeId;
	GoogleAPIResourcesEnum resourcesEnum;


	@Given("valid {string} request payload {string} {string} {string} {string}")
	public void valid_request_playload(String requestType, String name,String language,String address, String addressToUpdate) throws IOException {
		if(requestType.equalsIgnoreCase("POST")) {
			reqSpec= given().spec(GoogleAPIRequestSpecifications.ReqSpec()).body(GoogleApiPayLoads.addPlaceRequestPayLoad( name, language, address));
			resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		}
		else if (requestType.equalsIgnoreCase("PUT")) {
			reqSpec= given().spec(GoogleAPIRequestSpecifications.ReqSpec()).body(GoogleApiPayLoads.updatePlaceRequestPayLoad(placeId, addressToUpdate));
		
		}
		else if (requestType.equalsIgnoreCase("DELETE")) {
			reqSpec= given().spec(GoogleAPIRequestSpecifications.ReqSpec()).body(GoogleApiPayLoads.DeleteGoogleAPIPojo(placeId));
		
		}
	
	}
	@When("submit the {string} with {string} request")
	public void submit_the_post_request(String resourceName,String requestType) {
		
		 resourcesEnum= GoogleAPIResourcesEnum.valueOf(resourceName);
		System.out.println(resourcesEnum.getResource());
		
		switch(requestType)
		{
		case "POST":{
			res=reqSpec.when().post(resourcesEnum.getResource());
			System.out.println("Response for Add Place API" +res.asString());
			break;
		}
		case "GET" :{
			res=reqSpec.when().get(resourcesEnum.getResource());
			break;
		}
		case "PUT":{
			res=reqSpec.when().put(resourcesEnum.getResource());	
			break;
		}
		
		case "DELETE":{
			res=reqSpec.when().delete(resourcesEnum.getResource());
	        break;
		}
		}
		
		
	}
	
	@Then("validate whether the reponse is {int}")
	public void validate_whether_the_reponse_is(int expectedStatusCode) {
        assertEquals("Status code mismatch", expectedStatusCode, res.getStatusCode());
	}
	
	@Then("validate the {string} field of the response body is {string}")
	public void validate_the_field_of_the_response_body_is(String key, String expectedvalue) {
		
	    assertEquals("field "+key+"value mismatch",expectedvalue,getJsonPathString(res, key));  
	}
	
	@And("Fetch the place_id from the reponse")
		public void fetchThePlaceId() {
			 placeId= getJsonPathString(res,"place_id");
		}
	
	@Then("verify {string} created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String keyvalue, String nameField, String resourceName) throws IOException {
		reqSpec= given().spec(GoogleAPIRequestSpecifications.ReqSpec()).queryParam("place_id", placeId);
		submit_the_post_request( resourceName,"GET");
		assertEquals("name fiels with the expected is not matching" ,nameField, getJsonPathString(res,keyvalue));	  
	}
	

	@When("submit the {string} with {string} request wrong test data")
	public void submit_the_with_request_wrong_test_data(String resourceName, String requestType) {
		 resourcesEnum= GoogleAPIResourcesEnum.valueOf(resourceName);
			System.out.println(resourcesEnum.getResource());
			
			switch(requestType)
			{
			case "POST":{
				res=reqSpec.when().post(resourcesEnum.getResource());
				System.out.println("Response for Add Place API" +res.asString());
				break;
			}
			case "GET" :{
				res=reqSpec.when().get(resourcesEnum.getResource());
				break;
			}
			case "PUT":{
				res=reqSpec.when().put(resourcesEnum.getResource());	
				break;
			}
			
			case "DELETE":{
				res=reqSpec.when().delete(resourcesEnum.getResource());
		        break;
			}
			}
		
		
	}

}
