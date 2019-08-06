package com.qa.restAssuredTestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.util.RestUtills;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * TC002 :: Register Customer API
 * 
 * @author akjain
 *
 */
public class TC002_POST_Request_Register_Customer {

	@SuppressWarnings("unchecked")
	@Test
	void RegistrationSuccessful()
	{

		//Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/customer";

		//Request object
		RequestSpecification httpRequest=RestAssured.given();


		//Request paylaod sending along with post request
		JSONObject requestParams=new JSONObject();

		requestParams.put("FirstName", RestUtills.getFirstName());
		requestParams.put("LastName", RestUtills.getLastName());
		requestParams.put("UserName", RestUtills.getUserName());
		requestParams.put("Password", RestUtills.getPassword());
		requestParams.put("Email", RestUtills.getEmail());

		httpRequest.header("Content-Type","application/json");

		httpRequest.body(requestParams.toJSONString()); // attach above data to the request

		//Response object
		Response response=httpRequest.request(Method.POST,"/register");


		//print response in console window

		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);

		//status code validation
		int statusCode=response.getStatusCode();
		System.out.println("Status code is: "+statusCode);
		Assert.assertEquals(statusCode, 201);

		//success code validation
		String successCode=response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
		String message=response.jsonPath().get("Message");
		Assert.assertEquals(message, "Operation completed successfully");

	}

}
