package main;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import payload.Payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
		
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
//		given().log().all().queryParam("key", "qaclick123", "place_id","7d43a94b14f06faa301df5283fd59119").header("Content-Type","application/json").when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200);
		
//		given().log().all().queryParam("key", "qaclick123", "place_id","7d43a94b14f06faa301df5283fd59119").header("Content-Type","application/json").when().log().all().get("maps/api/place/get/json?key=qaclick123&place_id=7d43a94b14f06faa301df5283fd59119").then().log().all();
		
		
		System.out.println("*".repeat(50));
		
//		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(Payload.addPlace()).when().log().all().put("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).headers("Server", "Apache/2.4.52 (Ubuntu)");
		
		Response put = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(Payload.addPlace()).when().log().all().put("maps/api/place/add/json");
		
		ValidatableResponse then = put.then();
		
		System.out.println("=".repeat(100));
		
		String body = then.extract().body().asString();
		System.out.println("38 "+"Body - "+body);
		
		System.out.println("*".repeat(50));
		
		String header = then.extract().headers().asList().toString();
		System.out.println("43 "+"Header - "+header);
		
		System.out.println("*".repeat(50));
		
		String response = then.extract().response().asString();
		System.out.println("48 "+"Response - "+response);
		
		JsonPath bodyJsonPath = new JsonPath(body);
		System.out.println("*".repeat(50));
		System.out.println("52 "+"Body Json "+bodyJsonPath.prettify());
		
		
		JsonPath responseJsonPath = new JsonPath(response);
		System.out.println("*".repeat(50));
		System.out.println(responseJsonPath.prettify());
		String placeId = responseJsonPath.getString("place_id");
		System.out.println("59 "+"place id - "+ placeId);
		
		
		System.out.println("+".repeat(40)+" <<<Update Address>>> " + "+".repeat(40));
		String newAddress = "Balisankara-1234560";
		given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json").body(Payload.updateAddress(placeId, newAddress)).when().log().all().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		System.out.println("+".repeat(40)+" <<<Get Address>>> " + "+".repeat(40));
		
		String updatedResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId).when().log().all().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("70 "+"Updated response - "+updatedResponse);
		
		JsonPath newResponse = new JsonPath(updatedResponse);
		System.out.println("73 "+newResponse.prettify());
		
		String newUpdatedAddress = newResponse.getString("address");
		System.out.println("76 "+"New updated address - "+newUpdatedAddress);
		
		Assert.assertEquals(newUpdatedAddress, newAddress);
		System.out.println("81 - " + newUpdatedAddress.equals(newAddress));
	}
}
