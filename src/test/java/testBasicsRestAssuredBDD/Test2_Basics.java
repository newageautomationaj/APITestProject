package testBasicsRestAssuredBDD;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.qa.data.Users;

import io.restassured.RestAssured;

public class Test2_Basics {
	
	//@Test
	public void getMethod() {
		RestAssured.baseURI = "https://reqres.in";
		given()
			.contentType("application/json")
		.when()
			.get("/api/users")
		.then()
			.log().all();

	}
	
	//@Test
	public void postMethod() {
		JSONObject json = new JSONObject();
		int randomId = new Random().nextInt(1000);
		System.out.println(randomId);
		json.put("name", "arun jain");
		json.put("job", "SDET");
		json.put("id", randomId);
		
		RestAssured.baseURI = "https://reqres.in";
		given()
			.header("Content-Type", "application/json")
			.and()
			.body(json.toString())
		.when()
			.post("/api/users")
		.then()
			.statusCode(201)
			.and()
			.log().all();
	}
	
	//@Test
	public void postMethodWithJSONFile() throws FileNotFoundException {
		File file = new File(".\\JsonDataSet\\JsonDataFile.json");
		RestAssured.baseURI = "https://reqres.in";
		given()
			.header("Content-Type", "application/json")
			.and()
			.body(file)
		.when()
			.post("/api/users")
		.then()
			.statusCode(201)
			.and()
			.log().all();
	}
	
	@Test
	public void postMethodWithJavaObject() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		System.out.println(instant);
		
		Users users = new Users("Varun jain", "test engineer" ,"123", instant.toString());
		RestAssured.baseURI = "https://reqres.in";
		given()
			.header("Content-Type", "application/json")
			.and()
			.body(users)
		.when()
			.post("/api/users")
		.then()
			.statusCode(201)
			.and()
			.log().all();
	}

}
