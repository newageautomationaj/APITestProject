package testBasicsRestAssuredBDD;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Test1_BasicFeatures {

	/**
	 * simply checking the status code
	 */
	//@Test
	public void testStatusCode() {
		given().get("http://jsonplaceholder.typicode.com/posts/3").
		then().statusCode(200);

	}
	
	/**
	 * it will verify the code and print complete response in console
	 */
	@Test
	public void testLogging() {
		given().get("http://jsonplaceholder.typicode.com/posts/3").
		then().statusCode(200).log().all();

	}

	/**
	 * verify single content using org.hamcrest.Mattcher libraries
	 */
	@Test
	public void testEqualToFuntion() {
		given().get("http://jsonplaceholder.typicode.com/posts/3").
		then().statusCode(200).log().all();

	}
}
