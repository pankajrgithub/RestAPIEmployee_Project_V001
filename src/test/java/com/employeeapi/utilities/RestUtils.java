package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String getEmpName() {
		
		String generateName=RandomStringUtils.randomAlphanumeric(1);
		return ("John"+generateName);
	}
	
	public static String getEmpSal() {
	String generateSal=	RandomStringUtils.randomNumeric(4);
	return generateSal;
	}
	
	public static String getEmpAge() {
		String generateAge=RandomStringUtils.randomNumeric(2);
		return generateAge;
	}
}
