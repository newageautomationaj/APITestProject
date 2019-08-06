
package com.employeeAPI.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

/**
 * Test Name : Delete an employee record
 * 
 * @author akjain
 *
 */
public class TC005_Delete_Employee_Details extends TestBase {

	@BeforeClass
	void getAllEmployee() throws InterruptedException {
		logger.info("********** Started TC005_Delete_Employee_Details **********");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest	= RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		
		//First get the JsonPath object instance from the Response interface
		JsonPath jsonPath = response.jsonPath();
		
		//capture first ID
		empID = jsonPath.get("[0].id");
		System.out.println(empID);
		response = httpRequest.request(Method.DELETE, "/delete/"+empID); //pass id to delete API

		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		logger.info("********** Checking Response Body **********");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> "+responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
	}

	@Test
	void checkStatusCode() {
		logger.info("********** Checking Status Code **********");
		int statusCode = response.getStatusCode(); //Getting Status Code
		logger.info("Status Code is ==> "+statusCode); //200
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkResponseTime() {
		logger.info("********** Checking Response Time **********");
		long responseTime = response.getTime(); //Getting ResponseTime
		logger.info("Response Time is ==> "+responseTime);
		if(responseTime>5000)
			logger.warn("Response Time is greater then 5000");
		Assert.assertTrue(responseTime<5000);
	}

	@Test
	void checkStatusLine() {
		logger.info("********** Checking Status Line **********");
		String statusLine = response.getStatusLine(); //Getting Status Line
		logger.info("Status Line is ==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@AfterClass
	void tearDown() {
		logger.info("********** Finished TC005_Delete_Employee_Details **********");
	}

}
