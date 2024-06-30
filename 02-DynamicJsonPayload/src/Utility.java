import io.restassured.path.json.JsonPath;

public class Utility {
	public static JsonPath rawToJson(String rawData) {
		JsonPath js = new JsonPath(rawData);
		return js;
	}
}
