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
		String bookID = js.get("ID");
		System.out.println("Book id - "+bookID);
		
		//delete book
		String deleteResponseMessage = given().header("Content-Type", "application/json").body("{\"ID\":\""+bookID+"\"}")
		.when().put("/Library/DeleteBook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println("*".repeat(50));
		System.out.println("Book id - "+bookID);
		System.out.println("Delete response message - "+deleteResponseMessage);
		JsonPath deleteJS = Utility.rawToJson(deleteResponseMessage);
		System.out.println("*".repeat(50));
		System.out.println(deleteJS.prettify());
	}
	
	@DataProvider(name="bookDataProvider")
	public Object[][] bookData(){
		return new Object[][] {{"nsv", "000"},{"nsv", "111"},{"nsv", "222"},{"nsv", "333"}};
	}
}
