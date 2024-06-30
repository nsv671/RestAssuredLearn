import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class APItestingUsingExternalJSONfile {
	@Test
	public void addBookUsingExternalJSONfile() throws IOException {
		String JSONfilePath = "C:\\Users\\nites\\Desktop\\learnRestAssured\\02-DynamicJsonPayload\\src\\testData.json";
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json")
		.body(APItestingUsingExternalJSONfile.GenerateStringFromResource(JSONfilePath))
		.when().put("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println("response - "+response);
		System.out.println("*".repeat(50));
	}
	
	public static String GenerateStringFromResource(String path) throws IOException{
		 Path filePath = Paths.get(path);
		 byte[] byteData = Files.readAllBytes(filePath);
		 String stringJson = new String(byteData);
		 
		 System.out.println(stringJson);
		 return stringJson;
		 
	}
}
