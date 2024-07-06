import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		Location location = new Location(-38.383494, 33.427362);
		
		AddPlace addPlace = new AddPlace();
		
		addPlace.setAccuracy(50);
		addPlace.setAddress("29, side layout, cohen 09");
		addPlace.setLanguage("French-IN");
		addPlace.setLocation(location);
		addPlace.setName("Frontline house");
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setTypes(new String[]{"shoe park", "shop"});
		addPlace.setWebsite("http://google.com");
		
		
		String responseMessage = given().log().all().queryParam("key", "qaclick123").body(addPlace)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().asString();
	}

}
