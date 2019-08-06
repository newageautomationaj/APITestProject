package testBasicsRestAssuredBDD;

import static io.restassured.RestAssured.given;

import java.sql.Timestamp;
import java.time.Instant;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qa.data.UserResponse;
import com.qa.data.Users;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test2_PostResponseValidation {

	//@Test
	public void postMethodWithJavaObject() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		System.out.println(instant);
		
		Users users = new Users("Varun jain", "test engineer", "123", instant.toString());
		UserResponse userResponse = new UserResponse();
		Gson gson = new GsonBuilder().create();

		RestAssured.baseURI = "https://reqres.in";

		Response response =
				given()
				.header("Content-Type", "application/json")
				.and()
				.body(users)
				.when()
				.post("/api/users")
				.then()
				.statusCode(201)
				.and()
				.log().all()
				.and()
				.extract().response();

		userResponse = gson.fromJson(response.prettyPrint(), UserResponse.class);

		Assert.assertEquals(users.getName(), userResponse.getName());
		Assert.assertEquals(users.getJob(), userResponse.getJob());
		Assert.assertEquals(users.getId(), userResponse.getId());
		Assert.assertEquals(users.getCreatedAt(), userResponse.getCreatedAt());
	}
	
	@Test
	public void postMethodWithJsonString() {
		RestAssured.baseURI = "https://reqres.in";

		Response response =
			given()
				.header("Content-Type", "application/json")
				.and()
				.body("{\r\n" + 
						"\"name\":\"arun jain\",\r\n" + 
						"\"job\":\"sdet\",\r\n" + 
						"\"id\":null,\r\n" + 
						"\"createdAt\":null\r\n" + 
						"}")
			.when()
				.post("/api/users")
			.then()
				.statusCode(201)
				.and()
				.log().all()
				.and()
				.extract().response();

		JsonPath jsXpath = new JsonPath(response.asString());
		System.out.println("ID is --> "+jsXpath.get("id"));
	}

}
