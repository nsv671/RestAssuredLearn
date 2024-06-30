package payload;

import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath jsonPath = new JsonPath(Payload.coursePurchageDetails());
		
//		System.out.println(jsonPath.prettify());
		
		String course = jsonPath.getString("courses");
		System.out.println("*".repeat(50));
		System.out.println(course);
		
		// Print No of courses returned by API
		int courseSize = jsonPath.getInt("courses.size()");
		System.out.println("*".repeat(50));
		System.out.println("Course size - "+courseSize);
		
		// Print purchase amount returned by API
		String totalPurchaseAmountFromAPI = jsonPath.getString("dashboard.purchaseAmount");
		System.out.println("Purchase amoun - "+totalPurchaseAmountFromAPI);
		
		// Print title of first course returned by API
		String firstCourseTitle = jsonPath.getString("courses[0].title");
		System.out.println("*".repeat(50));
		System.out.println("First course title - "+firstCourseTitle);
		
		//Print no of copies sold by RPA Course
		System.out.println("*".repeat(30)+" Print no of copies sold by RPA Course "+"*".repeat(30));
		for(int i=0; i<courseSize ;i++) {
			String courseTitle = jsonPath.getString("courses["+i+"].title");
			int coursePrice = 0;
			int courseCopies = 0;
			
			if(courseTitle.equals("RPA")) {
				coursePrice = jsonPath.getInt("courses["+i+"].price");
				courseCopies = jsonPath.getInt("courses["+i+"].copies");
				System.out.printf("Title = %s , price = %d , Copies = %d \n", courseTitle, coursePrice, courseCopies);
				break;
			}
		}
		
		// Print course price returned by API
		// first attempt
		System.out.println("*".repeat(50));
		List<Object> coursesDetails = jsonPath.getList("courses");
		
		int totalCalculatedPurchaseAmount=0;
		for(Object currentCourse : coursesDetails) {
			System.out.println("*".repeat(50));
			String tempString = currentCourse.toString();
			
			String operationString = tempString.substring(1, tempString.length()-1);
			System.out.println(operationString);
			System.out.println("*".repeat(50));
			
			String[] finalArray = operationString.split(",");
			int price=0, copies=0;
			for(String item : finalArray) {
				System.out.println("=".repeat(50));
				
				String[] resultArray = item.split("=");
				System.out.println(item.split("=")[0]+" --> "+item.split("=")[1]);
				
				for(String element : resultArray) {
					if(element.trim().equals("price")) {
						price = Integer.valueOf(resultArray[1]);
						System.out.println("Price is assign");
					} else if(element.trim().equals("copies")) {
						copies = Integer.valueOf(resultArray[1]);
						System.out.println("copies is assign");
					}
				}
				
			}
			
			System.out.println("*".repeat(50));
			System.out.println("Total price - "+(price*copies)+" Course price - "+price+" number of copies - "+copies);
			totalCalculatedPurchaseAmount += price*copies;
		}
		
		System.out.println("*".repeat(50));
		System.out.println("Calculated amount - "+totalCalculatedPurchaseAmount+", API total purchase amount - "+totalPurchaseAmountFromAPI);
		Assert.assertEquals(totalCalculatedPurchaseAmount, Integer.valueOf(totalPurchaseAmountFromAPI));
		
		// Print course price returned by API
		// second attempted
		System.out.println("*".repeat(50));
		int numberOfCourse = jsonPath.getInt("courses.size()");
		int totalCalculatedPriceSecondAttempted = 0;
		for(int i=0; i<numberOfCourse ;i++) {
			String course2ndAttempted = jsonPath.getString("courses["+i+"].title");
			int price2ndAttempted = jsonPath.getInt("courses["+i+"].price");
			int copies2ndAttempted = jsonPath.getInt("courses["+i+"].copies");
			
			System.out.printf("Title = %s , price = %d , Copies = %d \n", course2ndAttempted, price2ndAttempted, copies2ndAttempted);
			totalCalculatedPriceSecondAttempted += price2ndAttempted*copies2ndAttempted;
		}
		
		System.out.println("*".repeat(50));
		System.out.println("Calculated amount - "+totalCalculatedPriceSecondAttempted+", API total purchase amount - "+totalPurchaseAmountFromAPI);
		Assert.assertEquals(totalCalculatedPriceSecondAttempted, Integer.valueOf(totalPurchaseAmountFromAPI));
		
	}

}
