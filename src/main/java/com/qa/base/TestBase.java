package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public int REPONSE_STATUS_CODE_200 = 200;
	public int REPONSE_STATUS_CODE_201 = 201;
	public int REPONSE_STATUS_CODE_300 = 300;
	public int REPONSE_STATUS_CODE_301 = 301;
	public int REPONSE_STATUS_CODE_400 = 400;
	public int REPONSE_STATUS_CODE_401 = 401;
	public int REPONSE_STATUS_CODE_500 = 500;
	
	
	public Properties prop;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			prop.load(fin);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
