package restAssuredBDDTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class Demo4_DELETE_Request {
	
	int emp_id = 564;
	
	@BeforeClass
	public void postData() {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		RestAssured.basePath = "/delete/"+emp_id;
	}
	
	@Test
	public void testDeleteRequest() {
		Response response = given()
		.when()
			.delete()
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.log().all()
			.extract().response();
		
		String jsonAsString = response.asString();
		Assert.assertEquals(jsonAsString.contains("successfully! deleted Records"), true);
	}

}
