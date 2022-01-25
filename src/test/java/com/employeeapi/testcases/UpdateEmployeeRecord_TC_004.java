package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.utilities.RestUtils;
import com.employeespi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class UpdateEmployeeRecord_TC_004 extends TestBase {

	String empName=RestUtils.getEmpName();
	String empSal=RestUtils.getEmpSal();
	String empAge=RestUtils.getEmpAge();
	
	@BeforeClass
	public void createEmpPost() {
		logger.info("************UpdateEmployeeRecord_TC_004 Started***************");
	
	 RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	 
	//Create Json object to put value on this
	  httpRequest=RestAssured.given();
	 JSONObject reqParam=new JSONObject();	
	
	reqParam.put("name", empName);
	reqParam.put("salary", empSal);
	reqParam.put("age", empAge);
	
	//set header type to send request type
	
	httpRequest.header("Content-Type","application/json");
	
	
	//set body request
	httpRequest.body(reqParam.toJSONString());
	
	//create response Object	
	response=httpRequest.request(Method.PUT,"/update/"+empid);
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
	Assert.assertEquals(message, "Successfully! Record has been updated.");
	}
	
	@Test
	public void tearDown() {
		logger.info("**************UpdateEmployeeRecord_TC_004 Test finished********************");
	}
}
