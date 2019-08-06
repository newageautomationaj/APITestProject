package com.qa.restAssuredTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * TC001 :: Weather API - Validate Status Code & Status Line
 * 
 * @author akjain
 *
 */
public class TC006_GET_Request_ExtractValueOfEachNodeInJSON {

	@Test
	void getweatherDetails()
	{
		//Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";

		//Request object
		RequestSpecification httpRequest=RestAssured.given();

		//Response object
		Response response=httpRequest.request(Method.GET,"/Delhi");

		JsonPath json = response.jsonPath();
		
		//Print all the nodes are available in JSON
		System.out.println("City : "+json.get("City"));
		System.out.println("Temperature : "+json.get("Temperature"));
		System.out.println("Humidity : "+json.get("Humidity"));
		System.out.println("Weather Description : "+json.get("WeatherDescription"));
		System.out.println("Wind Speed : "+json.get("WindSpeed"));
		System.out.println("Wind Direction Degree : "+json.get("WindDirectionDegree"));

		//validate for Temperature
		Assert.assertEquals(json.get("Temperature"), "33.49 Degree celsius");
	}

}
