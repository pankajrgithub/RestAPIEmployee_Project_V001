package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeespi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class DeleteEmployeeRecord_TC_005 extends TestBase{

	@BeforeClass
	public void setUp() {
		logger.info("************DeleteEmployeeRecord_TC_005 started*****************");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		//create request
		httpRequest=RestAssured.given();
		
		//create response object
		
		response=httpRequest.request(Method.DELETE,"/delete/"+empid);
	}
	
	@Test
	public void checkResponseBody() {
		logger.info("****************checking response body*************************");
		String responseBody=response.getBody().asString();
		logger.info("respondy is:======>"+responseBody);
		Assert.assertEquals(responseBody.contains("success"), true);
	}
	
	@Test
	public void checkHeaderType() {
		logger.info("****************checking response header*************************");
		String header=response.header("Content-Type");
		logger.info("Header is====>"+header);
		Assert.assertEquals(header, "application/json");
	}
	
	//check status line
	@Test
	public void checkStatusLine() {
		logger.info("**************checking status Line********************");
		String statusLine=response.getStatusLine();
		logger.info("status Line==========>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	@Test
	public void checkStatusCode() {
		logger.info("**************checking response code********************");
		int responseCode=response.getStatusCode();
		logger.info("response body============>"+responseCode);
		Assert.assertEquals(responseCode, 200);
		
	}
	
	@Test
	public void checkMessage() {
	logger.info("***************checking message****************************");
	String message=	response.jsonPath().get("message");
	logger.info("message is:====>"+message);
	Assert.assertEquals(message, "Successfully! Record has been deleted");
	}
	
	@Test
	public void tearDown() {
		logger.info("**************DeleteEmployeeRecord_TC_005 Test finished********************");
	}

}
