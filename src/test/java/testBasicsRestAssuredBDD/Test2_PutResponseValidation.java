package testBasicsRestAssuredBDD;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test2_PutResponseValidation {

	@Test
	public void putMethodWithJsonString() {
		RestAssured.baseURI = "https://reqres.in";

		Response response =
			given()
				.header("Content-Type", "application/json")
				.and()
				.body("{\r\n" + 
						"    \"name\": \"morpheus\",\r\n" + 
						"    \"job\": \"zion resident\"\r\n" + 
						"}")
			.when()
				.put("/api/users/2")
			.then()
				.statusCode(200)
				.and()
				.log().all()
				.and()
				.extract().response();

		JsonPath jsXpath = new JsonPath(response.asString());
		System.out.println("Job is --> "+jsXpath.get("job"));
	}

}
