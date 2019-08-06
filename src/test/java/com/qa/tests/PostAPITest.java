package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {

	TestBase testBase;
	String serviceURL;
	String apiURL; 
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeTest
	public void setup(){
		testBase = new TestBase();
		serviceURL = prop.getProperty("url");
		apiURL = prop.getProperty("serviceURL");

		url = serviceURL+apiURL;
	}

	@Test(priority=1)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		//jeckson API:
		ObjectMapper mapper = new ObjectMapper();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Instant instant = timestamp.toInstant();
		System.out.println(instant);
		
		Users users = new Users("arun jain", "sdet", "123", instant.toString()); //Expected User Object

		//Marshaling
		//object to JSON file:
		mapper.writeValue(new File(System.getProperty("user.dir")+"/src/main/java/com/qa/data/users.json"), users);

		//Java Object to JSON in String:
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);

		//Call the API:
		closeableHttpResponse = restClient.post(url, usersJsonString, headerMap);

		//Validate Response from the API:
		//1. Status Code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ---> "+ statusCode);
		Assert.assertEquals(statusCode, REPONSE_STATUS_CODE_201, "Status code is not 201");

		//2. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The Response from API is :: "+responseJson);
		
		//Unmarshaling
		//JSON to Java Object
		Users userResObj = mapper.readValue(responseString, Users.class); //Actual User Object
		System.out.println(userResObj);
		
		Assert.assertTrue(users.getName().equals(userResObj.getName()));
		
		Assert.assertTrue(users.getJob().equals(userResObj.getJob()));
		
		System.out.println(userResObj.getId());
		System.out.println(userResObj.getCreatedAt());
		
	}

}
