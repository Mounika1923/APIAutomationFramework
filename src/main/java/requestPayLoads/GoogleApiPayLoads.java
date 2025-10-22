package requestPayLoads;

import java.util.Arrays;
import java.util.List;

import pojoClasses.GoogleApiParentPojo;
import pojoClasses.LocationObject;
import pojoClasses.UpdateGoogleAPIPojo;

public class GoogleApiPayLoads {
	
	
public static GoogleApiParentPojo addPlaceRequestPayLoad(String name ,String language,String address)
{
GoogleApiParentPojo googlepojo=	new GoogleApiParentPojo();
	LocationObject location = new LocationObject();
	location.setLat(-38.383494);  // Use double values
	location.setLng(33.427362);   // Use double values
	googlepojo.setLocation(location);
			googlepojo.setAccuracy(500);
			googlepojo.setAddress(address);
			googlepojo.setLanguage(language);
			googlepojo.setName(name);
			googlepojo.setPhone_number("8956535353");
			googlepojo.setWebsite("https://rahulshettty.com//");
			String[] types = {"most popular","most rated"};
			List<String> typesList =Arrays.asList(types);
			googlepojo.setTypes(typesList);
			
			return googlepojo;
}
	
	
public static UpdateGoogleAPIPojo updatePlaceRequestPayLoad(String placeId ,String address)
{
UpdateGoogleAPIPojo updatepojo=	new UpdateGoogleAPIPojo();
updatepojo.setAddress(address);
updatepojo.setKey("qaclick123");
updatepojo.setPlace_id(placeId);

			
			return updatepojo;
}


public static String DeleteGoogleAPIPojo(String placeId)
{
	return "{\"place_id\":\""+placeId+"\"}";
}

}
