package utils;

public enum GoogleAPIResourcesEnum {
	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	UpdatePlaceAPI("maps/api/place/update/json"),
    DeletePlaceAPI("maps/api/place/delete/json"),
	wrongAddPlaceAPI("maps/api/plac/add/json");
    
	String resource ;
	
	GoogleAPIResourcesEnum (String resource)
	{
		this.resource = resource;
		
	}
	
	public String getResource()
	{
		return resource;
	}

}
