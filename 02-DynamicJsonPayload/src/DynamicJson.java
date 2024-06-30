import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	@Test(dataProvider="bookDataProvider")
	public void addBook(String isbn, String asile) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json")
		.body(Payload.addBook(isbn, asile))
		.when().put("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println("response - "+response);
		System.out.println("*".repeat(50));
		
		JsonPath js = Utility.rawToJson(response);
		System.out.println(js.prettify());
		System.out.println("*".repeat(50));
		System.out.println("Message - "+js.get("Msg"));
		System.out.println("Book id - "+js.get("ID"));
		
		//delete book
		
//		Dynamically build json payload with external data inputs
//		Parameterize the API Tests with multiple data sets
//		How to send static Json files(payload) directly into Post Method of Rest Assured I
	}
	
	@DataProvider(name="bookDataProvider")
	public Object[][] bookData(){
		return new Object[][] {{"nsv", "111"}, {"snv", "222"}, {"vns", "333"}};
	}
}
