package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void runThisBeforeDeletePlaceSscenario() throws IOException {
		GoogleApiTestingSteps steps= new GoogleApiTestingSteps();
		steps.valid_request_playload("POST","mounishop","Telugu","guntur","");
		steps.submit_the_post_request("AddPlaceAPI", "POST");
		steps.fetchThePlaceId();
		
	}

}
