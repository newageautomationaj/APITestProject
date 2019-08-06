package com.employeeAPI.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtills {
	
	public static String getEmpName() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return ("John"+generatedString);
	}
	
	public static String getEmpSalary() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return (generatedString);
	}
	
	public static String getEmpAge() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return (generatedString);
	}

}
