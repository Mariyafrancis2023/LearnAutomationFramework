package com.automationexercise.LearnAutomationFramework.Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automationexercise.LearnAutomationFramework.Base.BaseClass;
import com.automationexercise.LearnAutomationFramework.Page.HomePage;


public class HomePageTest extends BaseClass{

	HomePage home;
	
	@BeforeMethod
	public void setUp() {
		initialization();
		home =  new HomePage();
	}
	
	@Test
	public void validateTitle() {
		home.clickWomenProductBtn();
		home.clickTopProductBtn();
		Assert.assertEquals(wd.getTitle(), "Automation Exercise - Tops Products", "incorrect page");
	}
	
	@AfterMethod
	public void quit() {
		tearDown();
	}
}
