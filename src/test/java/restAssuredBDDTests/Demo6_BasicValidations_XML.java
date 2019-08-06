package restAssuredBDDTests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/*
 * 
 1. Verifying single content in XML response body
 2. Verifying multiple contents in XML response body
 3. Verifying all the content in XML response body in one go
 4. Find values using XML Path in XML response
 5. XPath with text() function
 */

public class Demo6_BasicValidations_XML {

	//1. Verifying single content in response body
	@Test(priority=1)
	public void testSingleContent() {
		given()
		.when()
		.get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/15")
		.then()
		.statusCode(200)
		.body("CUSTOMER.ID", equalTo("15"))
		.log().all();
	}

	//2. Verifying multiple content in response body
	@Test(priority=2)
	public void testMultipleContent() {
		given()
		.when()
		.get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/15")
		.then()
		.statusCode(200)
		.body("CUSTOMER.ID", equalTo("15"))
		.body("CUSTOMER.FIRSTNAME", equalTo("Bill"))
		.body("CUSTOMER.LASTNAME", equalTo("Clancy"))
		.body("CUSTOMER.STREET", equalTo("319 Upland Pl."))
		.body("CUSTOMER.CITY", equalTo("Seattle"));
	}

	//3. Verifying all the content in XML response body in one go
	@Test(priority=3)
	public void testMultipleContentInOneGo() {
		given()
		.when()
		.get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/15")
		.then()
		.statusCode(200)
		.body("CUSTOMER.text()", equalTo("15BillClancy319 Upland Pl.Seattle"));
	}

	//4. Find values using XML Path in XML response
	@Test(priority=4)
	public void testUsingXPath1() {
		given()
		.when()
		.get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/15")
		.then()
		.statusCode(200)
		.body(hasXPath("/CUSTOMER/FIRSTNAME", containsString("Bill")));
	}

	//4. Find values using XML Path in XML response
	@Test(priority=5)
	public void testUsingXPath2() {
		given()
		.when()
		.get("http://www.thomas-bayer.com/sqlrest/INVOICE")
		.then()
		.statusCode(200)
		.body(hasXPath("/INVOICEList/INVOICE[text()='33']"))
		.log().all();
	}

}
