package test;


import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.CreateOrder;
import pojo.CreateProductOrderResponse;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.Order;

public class EcommerceAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("postman671@gmai.com");
		loginRequest.setUserPassword("Postman@671");
		
		//Login
		RequestSpecification loginReq = given().spec(requestSpecification).log().all().body(loginRequest);
		
		LoginResponse loginResponse = loginReq.when().post("/api/ecom/auth/login").then().log().all().extract().as(LoginResponse.class);
		
		String userId = loginResponse.getUserId();
		String loginToken = loginResponse.getToken();
		System.out.println(loginResponse.getMessage());
		
		//Add product
		
		RequestSpecification addProductBaseSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", loginToken).build();
		
		RequestSpecification createProductRequest = given().spec(addProductBaseSpecification).log().all().param("productName", "Naruto's Eye").param("productAddedBy", userId).param("productCategory", "Anime").param("productSubCategory", "Comics").param("productPrice", "123").param("productDescription", "Manga").param("productFor", "all").multiPart("productImage", new File("C:\\Users\\nites\\Desktop\\naruto wallpaper\\976509.png"));
		
		String createProductResponse = createProductRequest.when().log().all().post("/api/ecom/product/add-product").then().log().all().extract().asString();
		JsonPath js = new JsonPath(createProductResponse);
		
		String productId = js.get("productId");
		
		//Create order
		RequestSpecification createOrderSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", loginToken).setContentType(ContentType.JSON).build();
		
		//Order POJO
		Order order1 = new Order();
		order1.setCountry("India");
		order1.setProductOrderedId(productId);

		ArrayList<Order> orders = new ArrayList<>();
		orders.add(order1);
		
		CreateOrder createOrder = new CreateOrder();
		createOrder.setOrders(orders);
		
		RequestSpecification createOrderRequest = given().spec(createOrderSpecification).log().all().body(createOrder);
		CreateProductOrderResponse createProductOrderResponse= createOrderRequest.when().post("/api/ecom/order/create-order").then().log().all().extract().as(CreateProductOrderResponse.class);
		
		System.out.println("Orders --->"+Arrays.toString(createProductOrderResponse.getOrders()));
		System.out.println("Product ids--->"+Arrays.toString(createProductOrderResponse.getProductOrderId()));
		System.out.println("Message --->"+createProductOrderResponse.getMessage());
		System.out.println(createProductOrderResponse);
		
		//delete
		RequestSpecification deleteOrderSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", loginToken).build();
		
		//.relaxedHTTPSValidation() --> in case of SSL certificate error it will bypass the error
		RequestSpecification deleteProductRequest = given().relaxedHTTPSValidation().spec(deleteOrderSpecification).log().all();
		
		String[] productIds = createProductOrderResponse.getProductOrderId();
		
		for(String product : productIds) {
			deleteProductRequest.when().log().all().delete("/api/ecom/product/delete-product/"+product).then().log().all().extract().asString();
		}
	}

}
