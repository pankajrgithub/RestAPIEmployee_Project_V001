package com.employeespi.base;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger; 
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest ;
	public static Response response;
	public static String empid="2";
	public static Logger logger;
	@BeforeClass
	public void setup() {
		logger =Logger.getLogger("TestBase");
		PropertyConfigurator.configure("log4j.properties");	
	//	logger.setLevel(Level.DEBUG);
	}
}
