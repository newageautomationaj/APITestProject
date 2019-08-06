package testBasicsRestAssuredBDD;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Test2_DeleteResponseValidation {

	@Test
	public void deleteMethod() {
		RestAssured.baseURI = "https://reqres.in";

		/*Response response =*/
			given()
				.header("Content-Type", "application/json")
			.when()
				.delete("/api/users/2")
			.then()
				.statusCode(204)
				.and()
				.log().all()
				/*.and()
				.extract().response()*/;

		/*JsonPath jsXpath = new JsonPath(response.asString());
		System.out.println("ID is --> "+jsXpath.get("id"));*/
	}

}
