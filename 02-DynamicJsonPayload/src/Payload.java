
public class Payload {
	public static String addBook() {
		String bookDetails = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\"bcdnsv\",\r\n"
				+ "\"aisle\":\"671\",\r\n"
				+ "\"author\":\"John foe peete XXX\"\r\n"
				+ "}";
		
		return bookDetails;
	}
	
	public static String addBook(String isbn, String aisle) {
		String bookDetails = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe peete XXX\"\r\n"
				+ "}";
		
		return bookDetails;
	}
}
