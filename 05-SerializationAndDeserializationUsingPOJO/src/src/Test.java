package src;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		String tokenEndPoint = "oauthapi/oauth2/resourceOwner/token";
		String coursePath = "oauthapi/getCourseDetails";
		
		String getTokenPayload = "{\r\n"
				+ "    \"client_id\": \"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com\",\r\n"
				+ "    \"client_secret\": \"erZOWM9g3UtwNRj340YYaK_W\",\r\n"
				+ "    \"grant_type\": \"client_credentials\",\r\n"
				+ "    \"scope\": \"trust\"\r\n"
				+ "}";
		
		//get authentication token
//		String tokenResponse = given().header("Content-Type","application/json").body(getTokenPayload)
//				.when().post(tokenEndPoint).then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		String tokenResponse = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().post(tokenEndPoint).then().log().all().extract().asString();
		
		JsonPath js = new JsonPath(tokenResponse);
		String accessToken = js.getString("access_token");
		System.out.println("Access token - "+accessToken);
		System.out.println("*".repeat(100));
		
		//get course
		GetCourses courseResponse = given().queryParam("access_token",accessToken)
						.when().get(coursePath)
						.then().log().all().extract().as(GetCourses.class);
		
		System.out.println(courseResponse.getLinkedIn());
		System.out.println(courseResponse.getServices());
		
		WebAutomation[] wa = courseResponse.getCourses().getWebAutomation();
		Api[] api = courseResponse.getCourses().getApi();
		Mobile[] mobile = courseResponse.getCourses().getMobile();
		
		for(WebAutomation item : wa) {
			System.out.println("*".repeat(100));
			System.out.println(item.getCourseTitle());
			System.out.println(item.getPrice());
		}
		
		for(Api item : api ) {
			if(item.getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java")) {
				System.out.println("*".repeat(100));
				System.out.println(item.getCourseTitle());
				System.out.println(item.getPrice());
			}
		}
	}

}
