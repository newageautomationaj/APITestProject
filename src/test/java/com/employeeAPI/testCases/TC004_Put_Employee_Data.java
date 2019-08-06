
package com.employeeAPI.testCases;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;
import com.employeeAPI.utilities.RestUtills;

import io.restassured.RestAssured;
import io.restassured.http.Method;

/**
 * Test Name : Update an employee record
 * 
 * @author akjain
 *
 */
public class TC004_Put_Employee_Data extends TestBase {

	String empName = RestUtills.getEmpName();
	String empSal = RestUtills.getEmpSalary();
	String empAge = RestUtills.getEmpAge();

	@BeforeClass
	void getEmployeeData() throws InterruptedException {
		logger.info("********** Started TC004_Put_Employee_Data **********");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest	= RestAssured.given();

		//here we created data which we can send along with POST request
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", empName);
		requestParam.put("salary", empSal);
		requestParam.put("age", empAge);

		//Add a header stating that the Request body is JSON
		httpRequest.header("Content-Type","application/json");

		//Add the JSON to body of the request
		httpRequest.body(requestParam.toString()); // attach above data to the request

		//Response object
		response=httpRequest.request(Method.PUT,"/update/"+empID);

		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		logger.info("********** Checking Response Body **********");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> "+responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSal), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}

	@Test
	void checkStatusCode() {
		logger.info("********** Checking Status Code **********");
		int statusCode = response.getStatusCode(); //Getting Status Code
		logger.info("Status Code is ==> "+statusCode); //200
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkStatusLine() {
		logger.info("********** Checking Status Line **********");
		String statusLine = response.getStatusLine(); //Getting Status Line
		logger.info("Status Line is ==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		logger.info("********** Checking Content Type **********");
		String contentType = response.getHeader("Content-Type"); //Getting Content Type
		logger.info("Content Type is ==> "+contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkServerType() {
		logger.info("********** Checking Server Type **********");
		String server = response.getHeader("Server"); //Getting Server Type
		logger.info("Server Type is ==> "+server);
		Assert.assertEquals(server, "Apache");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("********** Finished TC004_Put_Employee_Data **********");
	}

}
