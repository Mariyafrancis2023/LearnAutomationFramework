package com.automationexercise.LearnAutomationFramework.Listeners;

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
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener extends TestListenerAdapter {

	 public ExtentHtmlReporter htmlReporter;
	  public ExtentReports extent;
	  public ExtentTest test;
	  
	  @Override
	  public void onStart(ITestContext testContext) {
		    //Generate time stamp
			String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
		  
			//Name of report
			String repName = "Report_" + timeStamp + ".html";
		  
			//Save the report
			htmlReporter = new ExtentHtmlReporter("./Reports/" + repName);
			
			//Load the config file
			htmlReporter.loadXMLConfig("./extent_config.xml");
			
			extent =  new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Name of Tester", "Maria");
			extent.setSystemInfo("Env", "Production");
			extent.setSystemInfo("Website", "https://www.automationexercise.com/");
	  }

	@Override
	  public void onTestSuccess(ITestResult tr) {	  
		test = extent.createTest(tr.getMethod().getMethodName()); 
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	  }

	  @Override
	  public void onTestFailure(ITestResult tr) {
		  test = extent.createTest(tr.getMethod().getMethodName()); 
		  test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
	  }

	  @Override
	  public void onTestSkipped(ITestResult tr) {
		  test = extent.createTest(tr.getMethod().getMethodName()); 
		  test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.AMBER));
	  }
	
	  @Override
	  public void onFinish(ITestContext testContext) {
		  extent.flush();
	  }
}

