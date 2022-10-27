package complexJson;

import Utils.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonValidations {

	public static void main(String[] args) {
//		parse the String payload usign JsonPath class
		JsonPath js = new JsonPath(Payload.complexJsonBody());

//		print the total purchase amount
		int totalPurchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total purchase amount : " + totalPurchaseAmount);

//		print the website name
		String websiteName = js.getString("dashboard.website");
		System.out.println("Website name is : " + websiteName);
//
//		print the number of courses
		int numberOfCourses = js.getInt("courses.size()");
		System.out.println("Number of courses : " + numberOfCourses);

//		print the tile of the first course
		String firstCourseTitle = js.getString("courses[0].title");
		System.out.println("First course title : " + firstCourseTitle);

//		I want to print the titles and prices of all the courses
		for (int i = 0; i < numberOfCourses; i++) {
			System.out
					.println(js.getString("courses[" + i + "].title") + " : " + js.getString("courses[" + i + "].price"));
		}
	}
}