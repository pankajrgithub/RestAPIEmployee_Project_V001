package com.employeeapi.testcases;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeespi.base.TestBase;

import io.restassured.RestAssured;

import io.restassured.http.Method;

public class GetAllEmployees_TC_001 extends TestBase{

	@BeforeClass
	public void getAllEmployee() throws InterruptedException {
	
		logger.info("************Started TC_001 get employees*************");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		httpRequest=RestAssured.given();
		
		response=httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(2000);
		
	}
	//check response body
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
	//check message="Successfully! All records has been fetched."
	@Test
	void checkSucessMessage() {
		logger.info("*************checking sucessfull message**********************");
		String message=response.jsonPath().get("message");
		logger.info("message is===>"+message);
		Assert.assertEquals(message,"Successfully! All records has been fetched.");
	}
	//check response time
	@Test
	void checkResponseTime() {
		logger.info("*************checking response time**********************");
		long responseTime=response.getTime();
		logger.info("Response Time===>"+responseTime);
		
		if(responseTime>2000) 
			logger.warn("response time greater than 2000");
		Assert.assertTrue(responseTime<2000);
		
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
	
	//check content-encoding
	public void checkContentEncoding() {
		logger.info("*************checking content-encoding**********************");
		String contentEncoding=response.getHeader("Content-Encoding");
		logger.info("Content-Encoding==>"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	//check contentType header value
	@Test
	void checkHeaderContent() {
		logger.info("**************Checking Response header*****************");
		String contentType=response.getHeader("Content-Type");
		logger.info("Contect-Type is ===>"+contentType);
		Assert.assertEquals(contentType, "application/json");
	}
	
	//check content length
	void checkContentLength() {
		logger.info("**************Checking Content Length*****************");
		String contentLength=response.getHeader("Content-Length");
		logger.info("Contect-length is ===>"+contentLength);
		if(Integer.parseInt(contentLength)<100)
			logger.info("Content length is less than 100");		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	
	//check cookies
	
	public void tearDown() {
		logger.info("**************finish TC_001 GetAllEmployees**********************");
	}
}
