package com.qa.restAssuredTestCases;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.util.XLUtills;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * TC008 :: Create new record in database
 * 
 * @author akjain
 *
 */
public class TC008_POST_Request_DataDrivenTest {

	@Test(dataProvider="empDataProviderExcel")
	void postNewEmployees(String eName, String eSal, String eAge)
	{
		//Specify base URI
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";

		//Request object
		RequestSpecification httpRequest=RestAssured.given();
		
		//here we created data which we can send along with POST request
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", eName);
		requestParam.put("salary", eSal);
		requestParam.put("age", eAge);
		
		//Add a header stating that the Request body is JSON
		httpRequest.header("Content-Type","application/json");
		
		//Add the JSON to body of the request
		httpRequest.body(requestParam.toString()); // attach above data to the request

		//Response object
		Response response=httpRequest.request(Method.POST,"/create");

		//capture the response body to perform validation
		String responseBody=response.getBody().asString();
		System.out.println("Employee Response Body is:" +responseBody);
		
		Assert.assertEquals(responseBody.contains(eName), true);
		Assert.assertEquals(responseBody.contains(eSal), true);
		Assert.assertEquals(responseBody.contains(eAge), true);

		//status code validation
		int statusCode=response.getStatusCode();
		System.out.println("Status code is: "+statusCode);
		Assert.assertEquals(statusCode, 200);

		//status line verification
		String statusLine=response.getStatusLine();
		System.out.println("Status line is: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}
	
	@DataProvider(name="empDataProvider")
	String [][]getEmpData(){
		String [][]empData = {{"abc4598","1459","23"},{"dert8589","5478","22"},{"gtr4488","2597","24"}};
		return empData;
	}
	
	@DataProvider(name="empDataProviderExcel")
	String [][]getEmpDataFromExcel() throws IOException{
		//Read data from Excel sheet
		String dataSheetPath = System.getProperty("user.dir")+"/testDataAndTestCases/API_TestCases.xlsx";
		String sheetName = "EmployeeTestData";
		
		int rowCount = XLUtills.getRowCount(dataSheetPath, sheetName);
		int colCount = XLUtills.getCellCount(dataSheetPath, sheetName, rowCount);
				
		String [][]empData = new String[rowCount][colCount];
		
		for(int i = 1; i <= rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				empData[i-1][j] = XLUtills.getCellData(dataSheetPath, sheetName, i, j);
			}
		}
		
		return empData;
	}

}
