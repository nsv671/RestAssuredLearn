package src;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
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
		
		// Request specification builder 
		// after providing the all the details we need to call build method just like Selenium Action class
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
//		String responseMessage = given().log().all().queryParam("key", "qaclick123").body(addPlace)
//		.when().post("/maps/api/place/add/json")
//		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		RequestSpecification requestBody = given().spec(requestSpecification).body(addPlace);
		Response response = requestBody.when().post("/maps/api/place/add/json");
		String responseMessage = response.then().spec(responseSpecification).extract().asString();
		
		System.out.println(responseMessage);
	}

}
