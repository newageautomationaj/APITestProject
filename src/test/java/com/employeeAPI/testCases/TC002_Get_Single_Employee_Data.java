
package com.employeeAPI.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

/**
 * Test Name : Get a single Employee Data
 * 
 * @author akjain
 *
 */
public class TC002_Get_Single_Employee_Data extends TestBase {

	@BeforeClass
	void getEmployeeData() throws InterruptedException {
		logger.info("********** Started TC002_Get_Single_Employee_Data **********");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest	= RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/"+empID);

		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		logger.info("********** Checking Response Body **********");
		String responseBody = response.getBody().toString();
		logger.info("Response Body ==> "+responseBody);
		Assert.assertTrue(responseBody!=null);
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

	@Test
	void checkContentEncoding() {
		logger.info("********** Checking Content Encoding **********");
		String contentEncoding = response.getHeader("Content-Encoding"); //Getting Content Encoding
		logger.info("Content Encoding is ==> "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@Test
	void checkContentLength() {
		logger.info("********** Checking Content Length **********");
		String contentLength = response.getHeader("Content-Length"); //Getting Content Length
		logger.info("Content Length is ==> "+contentLength);
		if(contentLength!=null) {
			if(Integer.parseInt(contentLength)<50)
				logger.warn("Content Length is less then 50");
			Assert.assertTrue(Integer.parseInt(contentLength)>50);
		}else {
			logger.warn("Content Length is null");
			Assert.assertEquals(contentLength,null);
		}
	}

	@Test
	void checkCookies() {
		logger.info("********** Checking Cookies **********");
		String cookies = response.getCookie("PHPSESSID"); //Getting Cookies
		logger.info("Cookies is ==> "+cookies);
		//Assert.assertEquals(cookies, "dfdgdgdgdgdfgfd");
	}

	@AfterClass
	void tearDown() {
		logger.info("********** Finished TC002_Get_Single_Employee_Data **********");
	}

}
