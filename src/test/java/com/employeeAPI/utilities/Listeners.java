package com.employeeAPI.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest text;
	
	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/APIReport_"+new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date())+".html");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Funtional (Rest Assured) API Testing Reports");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "Rest Assured API Project");
		extent.setSystemInfo("Host Name", "local host");
		extent.setSystemInfo("Environement", "QA");
		extent.setSystemInfo("User", "Arun Jain");
	}
	
	public void onTestSuccess(ITestResult result) {
		text = extent.createTest(result.getName());
		text.log(Status.PASS, "TEST CASE PASSED IS : "+result.getName());
	}
	
	public void onTestFailuar(ITestResult result) {
		text = extent.createTest(result.getName());
		text.log(Status.FAIL, "TEST CASE FAILED IS : "+result.getName());
		text.log(Status.FAIL, "TEST CASE FAILED IS : "+result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		text = extent.createTest(result.getName());
		text.log(Status.SKIP, "TEST CASE SKIPPED IS : "+result.getName());
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
