package com.automationexercise.LearnAutomationFramework.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.automationexercise.LearnAutomationFramework.Base.BaseClass;

public class Utils extends BaseClass {

	public static void takeScreenShot(String testName) {
		//Time stamp
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());

		//Take screenshot
		File screenshotFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		
		//Save screenshot
		try {
		FileUtils.copyFile(screenshotFile, new File("./ScreenShotsOfFailedTests\\" + "_" + testName + "_" + timeStamp + ".jpg"));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
