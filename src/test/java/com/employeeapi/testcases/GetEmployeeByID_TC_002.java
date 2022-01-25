package com.employeeapi.testcases;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeespi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetEmployeeByID_TC_002 extends TestBase{

	@BeforeClass
	public void getEmployeeById() throws InterruptedException {
	
		logger.info("************Started TC_002 get employee by ID*************");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		httpRequest=RestAssured.given();
		
		response=httpRequest.request(Method.GET,"/employee/"+empid);
		
		Thread.sleep(2000);
		
}
	@Test
	void checkResponseBody() {
		logger.info("*************checking response body**********************");
		String responseBody=response.getBody().asString();
		logger.info("Response body is===>"+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	//check status code
	@Test
    void checkStatusCode() {
		logger.info("*************checking status code**********************");
		int statusCode=response.getStatusCode();
		logger.info("Status Code is===>"+statusCode);
		Assert.assertEquals(statusCode,200);
    }
	//check response time
	@Test
	void checkResponseTime() {
		logger.info("*************checking response time**********************");
		long responseTime=response.getTime();
		logger.info("Response Time===>"+responseTime);
		
		if(responseTime>2000) 
			logger.warn("response time greater than 2000");
		Assert.assertTrue(responseTime<4000);		
	}
	
	//check status line
	void checkStatusLine() {
		logger.info("*************checking status Line**********************");
		String statusLine=response.getStatusLine();
		logger.info("Status Line is==>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	//check server type
	void checkServerType() {
		logger.info("*************checking server Type**********************");
		String serverType=response.getHeader("Server");
		logger.info("Server Type==>"+serverType);
		Assert.assertEquals(serverType, "nginx");
	}
	
	//tearDown
	void tearDown() {
		logger.info("*************Finished GetEmployeeByID_TC_002**********************");
	}
}
