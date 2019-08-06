package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtils;

public class GetAPITest extends TestBase {

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

	@Test(priority=0)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);

		//a. Status Code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ---> "+ statusCode);
		Assert.assertEquals(statusCode, REPONSE_STATUS_CODE_200, "Status code is not 200");

		//b. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		//System.out.println("Response JSON from API ---> "+ responseJson);
		
		//Single value Assertion
		//Response:: per_page
		String perPageValue = TestUtils.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per page ---> "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue),3, "Per Page value is not 3");

		//Response:: total
		String totalValue = TestUtils.getValueByJPath(responseJson, "/total");
		System.out.println("Value of Total ---> "+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue),12, "Total value is not 12");
		
		//Get Value from JSON Array
		String id = TestUtils.getValueByJPath(responseJson, "/data[0]/id");
		String email = TestUtils.getValueByJPath(responseJson, "/data[0]/email");
		String firstName = TestUtils.getValueByJPath(responseJson, "/data[0]/first_name");
		String lastName = TestUtils.getValueByJPath(responseJson, "/data[0]/last_name");
		String avatar = TestUtils.getValueByJPath(responseJson, "/data[0]/avatar");
		
		System.out.println("Value of ID at 0th Index in data node --> "+id);
		System.out.println("Value of Email at 0th Index in data node --> "+email);
		System.out.println("Value of First Name at 0th Index in data node --> "+firstName);
		System.out.println("Value of Last Name at 0th Index in data node --> "+lastName);
		System.out.println("Value of Avatar at 0th Index in data node --> "+avatar);

		//c. All Headers
		Header [] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new LinkedHashMap<String, String>();
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.err.println("Headers Array ---> "+allHeaders);
	}
	
	@Test(priority=1)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		/*headerMap.put("Username", "abc@gmail.com");
		headerMap.put("Password", "app123");
		headerMap.put("Auth Token", "12345");*/
		
		closeableHttpResponse = restClient.get(url, headerMap);

		//a. Status Code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ---> "+ statusCode);
		Assert.assertEquals(statusCode, REPONSE_STATUS_CODE_200, "Status code is not 200");

		//b. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		//System.out.println("Response JSON from API ---> "+ responseJson);
		
		//Single value Assertion
		//Response:: per_page
		String perPageValue = TestUtils.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per page ---> "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue),3, "Per Page value is not 3");

		//Response:: total
		String totalValue = TestUtils.getValueByJPath(responseJson, "/total");
		System.out.println("Value of Total ---> "+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue),12, "Total value is not 12");
		
		//Get Value from JSON Array
		String id = TestUtils.getValueByJPath(responseJson, "/data[0]/id");
		String email = TestUtils.getValueByJPath(responseJson, "/data[0]/email");
		String firstName = TestUtils.getValueByJPath(responseJson, "/data[0]/first_name");
		String lastName = TestUtils.getValueByJPath(responseJson, "/data[0]/last_name");
		String avatar = TestUtils.getValueByJPath(responseJson, "/data[0]/avatar");
		
		System.out.println("Value of ID at 0th Index in data node --> "+id);
		System.out.println("Value of Email at 0th Index in data node --> "+email);
		System.out.println("Value of First Name at 0th Index in data node --> "+firstName);
		System.out.println("Value of Last Name at 0th Index in data node --> "+lastName);
		System.out.println("Value of Avatar at 0th Index in data node --> "+avatar);

		//c. All Headers
		Header [] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new LinkedHashMap<String, String>();
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.err.println("Headers Array ---> "+allHeaders);
	}

}
