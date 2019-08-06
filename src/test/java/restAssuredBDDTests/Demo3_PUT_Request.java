package restAssuredBDDTests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.util.RestUtills;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.HashMap;


public class Demo3_PUT_Request {
	
	@SuppressWarnings("rawtypes")
	public static HashMap map = new HashMap();
	int emp_id = 1720;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void postData() {
		map.put("name", RestUtills.getEmpName());
		map.put("salary", RestUtills.getEmpSalary());
		map.put("age", RestUtills.getEmpAge());
				
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		RestAssured.basePath = "/update/"+emp_id;
	}
	
	@Test
	public void testPostRequest() {
		given()
			.contentType("application/json")
			.body(map)
		.when()
			.put()
		.then()
			.statusCode(200)
			.log().all();
	}

}
