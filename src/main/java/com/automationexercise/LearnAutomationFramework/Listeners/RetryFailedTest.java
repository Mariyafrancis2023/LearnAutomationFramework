package com.automationexercise.LearnAutomationFramework.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.automationexercise.LearnAutomationFramework.Base.BaseClass;

public class RetryFailedTest extends BaseClass implements IRetryAnalyzer {

	int maxCounter = 2;
	int counter = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		logger.info("Retrying failed test !!!!! " + result.getMethod().getMethodName() + " for " + counter + " times");
		if(counter < maxCounter) {
			counter++;
			return true;
		}
		return false;
	}

}
