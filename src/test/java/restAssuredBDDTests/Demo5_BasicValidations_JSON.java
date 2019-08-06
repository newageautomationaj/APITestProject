package restAssuredBDDTests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/*
 * 
 1. Test Status Code
 2. Log Response
 3. Verifying single content in response body
 4. Verifying multiple content in response body
 5. Setting parameters and headers
 */
public class Demo5_BasicValidations_JSON {

	//1. Test Status Code
	@Test(priority=1)
	public void testStatusCode() {
		given()
		.when()
			.get("https://jsonplaceholder.typicode.com/posts/2")
		.then()
			.statusCode(200);
	}

	//2. Log Response
	@Test(priority=2)
	public void testLogging() {
		given()
		.when()
			.get("https://jsonplaceholder.typicode.com/posts/3")
		.then()
			.statusCode(200)
			.log().all();
	}

	//3. Verifying single content in response body
	@Test(priority=3)
	public void testSingleContent() {
		given()
		.when()
			.get("https://reqres.in/api/users/1")
		.then()
			.statusCode(200)
			.body("data.email", equalTo("george.bluth@reqres.in"))
			.log().all();
	}

	//4. Verifying multiple content in response body
	@Test(priority=4)
	public void testMultipleContent() {
		given()
		.when()
			.get("https://reqres.in/api/users")
		.then()
			.statusCode(200)
			.body("data.first_name", hasItems("George","Janet","Emma"));
	}
	
	//5. Setting parameters and headers
	@Test(priority=5)
	public void testParametersAndHeaders() {
		given()
			.param("MyName", "Arun")
			.headers("MyHeader","Indian")
		.when()
			.get("https://reqres.in/api/users/1")
		.then()
			.statusCode(200);
	}

}
