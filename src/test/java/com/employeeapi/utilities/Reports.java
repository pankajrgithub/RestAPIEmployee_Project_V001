package com.employeeapi.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reports extends TestListenerAdapter{
	
	public ExtentReports report;
	public ExtentTest test;
	public ExtentSparkReporter htmlReport;
	
	public void onStart(ITestContext context) {
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		String reportName="Test-Report-"+timestamp+".html";
		
		htmlReport=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+reportName);
		
		try {
			htmlReport.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		report=new ExtentReports();
		report.attachReporter(htmlReport);
		
		report.setSystemInfo("Host name", "127.0.1");
		report.setSystemInfo("Environemnt", "QA Automation");
		report.setSystemInfo("User", "Pankaj");
		
		htmlReport.config().setDocumentTitle("Employees API Automation");
		htmlReport.config().setReportName("Employee API Automation Report");
		htmlReport.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr) {
		//create new entry in the report
		test=report.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
		
	}
	
	public void onTestFailure(ITestResult tr) {
		//create new entry in the report
		test=report.createTest(tr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
	}
	
	public void onTestSkipped(ITestResult tr) {
		//create new entry in the report
		test=report.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.YELLOW));
	}
	
	public void onFinish(ITestContext context) {
		report.flush();
	}
}
