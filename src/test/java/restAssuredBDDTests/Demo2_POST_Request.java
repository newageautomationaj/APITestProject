package restAssuredBDDTests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.util.RestUtills;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class Demo2_POST_Request {
	
	@SuppressWarnings("rawtypes")
	public static HashMap map = new HashMap();
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void postData() {
		map.put("FirstName", RestUtills.getFirstName());
		map.put("LastName", RestUtills.getLastName());
		map.put("UserName", RestUtills.getUserName());
		map.put("Password", RestUtills.getPassword());
		map.put("Email", RestUtills.getEmail());
		
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		RestAssured.basePath = "/register";
	}
	
	@Test
	public void testPostRequest() {
		given()
			.contentType("application/json")
			.body(map)
		.when()
			.post()
		.then()
			.statusCode(201)
			.and()
			.body("SuccessCode", equalTo("OPERATION_SUCCESS"))
			.body("Message", equalTo("Operation completed successfully"));
	}

}
