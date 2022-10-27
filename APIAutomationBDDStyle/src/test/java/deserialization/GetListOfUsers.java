package deserialization;

import static io.restassured.RestAssured.given;
import java.util.List;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.ListOfUsers;
import io.restassured.parsing.Parser;
import pojo.Data;
import pojo.ListOfUsers;

public class GetListOfUsers {
	@Test
	public void deserialization() {
//		https://reqres.in/api/users?page=2
		RestAssured.baseURI = "https://reqres.in";

//		ListOfUsers list = given().queryParam("page", "1").expect().defaultParser(Parser.JSON).when().get("api/users")
//				.then().assertThat().statusCode(200).extract().response().as(ListOfUsers.class);

		ListOfUsers list = given().queryParam("page", "2").expect().contentType(ContentType.JSON).when()
				.get("api/users").then().assertThat().statusCode(200).extract().response().as(ListOfUsers.class);

//		RestAssured will internally call the setter method of POJO classes and set the values for the variables defined inside that class
		System.out.println(list.getPage());
		System.out.println(list.getPer_page());
		System.out.println(list.getTotal());
		System.out.println(list.getTotal_pages());
		System.out.println(list.getSupport().getText());
		System.out.println(list.getSupport().getUrl());
//		System.out.println(list.getData().get(0).getFirst_name());

		List<Data> dataOfUser = list.getData();
		System.out.println(dataOfUser.size());

		for (int i = 0; i < dataOfUser.size(); i++) {
			System.out.println("******Details Of " + (i + 1) + "user*******");
			System.out.println(dataOfUser.get(i).getId());
			System.out.println(dataOfUser.get(i).getEmail());
			System.out.println(dataOfUser.get(i).getFirst_name());
			System.out.println(dataOfUser.get(i).getLast_name());
			System.out.println(dataOfUser.get(i).getAvatar());
		}
	}
}