package com.qa.restAssuredTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * TC005 :: Weather API - Validate JSON Response body
 * 
 * @author akjain
 *
 */
public class TC005_GET_Request_Validate_WeatherAPI_JSON_Body {

	@Test
	void getweatherDetails()
	{
		//Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";

		//Request object
		RequestSpecification httpRequest=RestAssured.given();

		//Response object
		Response response=httpRequest.request(Method.GET,"/Delhi");

		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);

		//validate for city
		Assert.assertEquals(responseBody.contains("Delhi"), true);
	}

}
