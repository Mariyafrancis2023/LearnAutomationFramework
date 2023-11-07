package com.automationexercise.LearnAutomationFramework.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import com.automationexercise.LearnAutomationFramework.Listeners.WebdriverEvents;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver wd;
	public static WebDriverWait wait;
	private FileInputStream fileInputStream;
	private Properties prop;
	public static Logger logger;
	private WebdriverEvents events;
	private EventFiringWebDriver eDriver;
	
	public BaseClass() {
		prop = new Properties();
		
		try {
			fileInputStream = new FileInputStream("C:\\Users\\me\\eclipse-workspace\\LearnAutomationFramework\\src\\main\\java\\com\\automationexercise\\LearnAutomationFramework\\Configuration\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	public void setUpLogger() {
		logger = Logger.getLogger(BaseClass.class);
		PropertyConfigurator.configure("log4j.properties");
		BasicConfigurator.configure();
		logger.setLevel(Level.ALL);
	}
	
	public void initialization() {
		String browserName = prop.getProperty("browser");
		switch (browserName) {
		case "chrome":
			wd = WebDriverManager.chromedriver().create();
			break;
		case "edge":
			wd = WebDriverManager.edgedriver().create();
			break;
		case "firefox":
			wd = WebDriverManager.firefoxdriver().create();
			break;
			default:
				System.out.println("Not a valid browser name");
				break;
		}
		
		eDriver = new EventFiringWebDriver(wd);
		events = new WebdriverEvents();
		eDriver.register(events);
		wd = eDriver;
		
		wd.get(prop.getProperty("URL"));
		wait = new WebDriverWait(wd, Long.parseLong(prop.getProperty("EXPLICIT_WAIT")));
		wd.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("IMPLICIT_WAIT")), TimeUnit.SECONDS);
		wd.manage().window().maximize();
	}
	
	public void tearDown() {
		wd.quit();
	}
}
