import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;

public class BugTest {
	public static void main(String[] args) { // TODO Auto-generated method stub
		String AUTHORIZATION_TOKEN = "Basic bml0ZXNoc2FoNjcxQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBrLXdLbUo5NjlsT2UwalY3NnFjVVFpODdvenBkX2Qwa1dwekd3eXM4Z3BwbFBZU2JwTXE1a2NlSWlZWkJaRm1WS1V0UnZHNVl4VTBqVVBnb1lKZ0c1WHhOTHluMndWMDFkZDNZSXNmU3RhU0JzYWNsaUEtbVl1Y1pFbEEtZ3V6LWtGbEtuSklRbTNqZDhXM05BeFRhTzk4VHNCSHItNlV1UEdHeGdhb2Y5OG89RjNEMjQzNEI=";
		RestAssured.baseURI = "https://niteshsah671.atlassian.net/";
		
		String createIssueResponse = given().header("Content-Type", "application/json").header("Authorization", AUTHORIZATION_TOKEN)
				.body("{\n" + "    \"fields\": {\n" + "       \"project\":\n" + "       {\n"
						+ "          \"key\": \"SCRUM\"\n" + "       },\n"
						+ "       \"summary\": \"My new issue - Rest Assured 😍😍😍\",\n"
						+ "       \"issuetype\": {\n" + "          \"name\": \"Bug\"\n" + "       }\n" + "   }\n" + "}")
				.log().all().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201)
				.contentType("application/json").extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		System.out.println("Issue ID - "+issueId);
		given()
		.header("X-Atlassian-Token", "no-check")
		.header("Authorization",AUTHORIZATION_TOKEN)
		.pathParam("key", issueId)
		.multiPart("file", new File("C:\\Users\\nites\\Desktop\\naruto wallpaper\\1011552.png")).log().all()
				.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}
}